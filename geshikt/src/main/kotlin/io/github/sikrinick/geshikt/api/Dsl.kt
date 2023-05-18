package io.github.sikrinick.geshikt.api

import com.google.api.services.sheets.v4.model.AddConditionalFormatRuleRequest
import com.google.api.services.sheets.v4.model.AddNamedRangeRequest
import com.google.api.services.sheets.v4.model.AddSheetRequest
import com.google.api.services.sheets.v4.model.BasicFilter
import com.google.api.services.sheets.v4.model.BooleanRule
import com.google.api.services.sheets.v4.model.Border
import com.google.api.services.sheets.v4.model.ConditionalFormatRule
import com.google.api.services.sheets.v4.model.DeleteNamedRangeRequest
import com.google.api.services.sheets.v4.model.DimensionProperties
import com.google.api.services.sheets.v4.model.GridProperties
import com.google.api.services.sheets.v4.model.MergeCellsRequest
import com.google.api.services.sheets.v4.model.NamedRange
import com.google.api.services.sheets.v4.model.RepeatCellRequest
import com.google.api.services.sheets.v4.model.Request
import com.google.api.services.sheets.v4.model.RowData
import com.google.api.services.sheets.v4.model.SetBasicFilterRequest
import com.google.api.services.sheets.v4.model.SheetProperties
import com.google.api.services.sheets.v4.model.UpdateBordersRequest
import com.google.api.services.sheets.v4.model.UpdateCellsRequest
import com.google.api.services.sheets.v4.model.UpdateDimensionPropertiesRequest
import com.google.api.services.sheets.v4.model.UpdateSheetPropertiesRequest


// Named ranges
internal fun Request.addNamedRange(block: NamedRange.() -> Unit) =
    setAddNamedRange(AddNamedRangeRequest().apply {
        namedRange = NamedRange().apply(block)
    })

internal fun Request.deleteNamedRange(name: String) =
    setDeleteNamedRange(DeleteNamedRangeRequest().apply {
        namedRangeId = name
    })

// Basic filter
internal fun Request.setBasicFilter(block: BasicFilter.() -> Unit) =
    setSetBasicFilter(SetBasicFilterRequest().apply { filter = BasicFilter().apply(block) })

// Borders
internal fun Request.updateBorders(block: UpdateBordersRequest.() -> Unit) =
    setUpdateBorders(UpdateBordersRequest().apply(block))
internal fun UpdateBordersRequest.border(block: Border.() -> Unit) =
    Border().apply(block)

// Conditional formatting
internal fun Request.conditionalFormatting(block: ConditionalFormatRule.() -> Unit) =
    setAddConditionalFormatRule(AddConditionalFormatRuleRequest().apply {
        rule = ConditionalFormatRule().apply(block)
    })
internal fun ConditionalFormatRule.booleanRule(block: BooleanRule.() -> Unit) =
    BooleanRule().apply(block)

// Update cells
internal fun Request.updateCells(block: UpdateCellsRequest.() -> Unit) =
    setUpdateCells(UpdateCellsRequest().apply(block).apply {
        fields = "*"
    })

internal fun UpdateCellsRequest.row(block: RowData.() -> Unit) {
    rows = rows ?: mutableListOf()
    rows.add(RowData().apply(block))
}

// Repeat cell
internal fun Request.repeatCell(block: RepeatCellRequest.() -> Unit) =
    setRepeatCell(RepeatCellRequest().apply(block).apply {
        fields = "*"
    })

// Merge cells
internal fun Request.mergeCells(block: MergeCellsRequest.() -> Unit) =
    setMergeCells(MergeCellsRequest().apply(block))

// Add sheet
internal fun Request.addSheet(block: AddSheetRequest.() -> Unit) =
    setAddSheet(AddSheetRequest().apply(block))

// Update sheet properties
internal fun Request.updateSheetProperties(block: UpdateSheetPropertiesRequest.() -> Unit) =
    setUpdateSheetProperties(UpdateSheetPropertiesRequest().apply(block))

internal fun sheetProperties(block: SheetProperties.() -> Unit) = SheetProperties().apply(block)
internal fun SheetProperties.gridProperties(block: GridProperties.() -> Unit) = GridProperties().apply(block)

// Update dimension properties (sheet)
internal fun Request.updateSheetDimensionProperties(block: UpdateDimensionPropertiesRequest.() -> Unit) =
    setUpdateDimensionProperties(UpdateDimensionPropertiesRequest().apply(block))
internal fun UpdateDimensionPropertiesRequest.dimensionProperties(block: DimensionProperties.() -> Unit) = DimensionProperties().apply(block)
internal fun DimensionProperties.gridProperties(block: GridProperties.() -> Unit) = GridProperties().apply(block)
