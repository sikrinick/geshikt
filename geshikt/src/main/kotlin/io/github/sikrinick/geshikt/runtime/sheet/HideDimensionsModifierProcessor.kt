package io.github.sikrinick.geshikt.runtime.sheet

import com.google.api.services.sheets.v4.model.UpdateDimensionPropertiesRequest
import io.github.sikrinick.geshikt.api.dimensionProperties
import io.github.sikrinick.geshikt.api.dimensionRange
import io.github.sikrinick.geshikt.api.request
import io.github.sikrinick.geshikt.api.updateSheetDimensionProperties
import io.github.sikrinick.geshikt.dsl.Sheet
import io.github.sikrinick.geshikt.dsl.invoke

class HideRowsProcessor(
    val sheet: Sheet
) : SheetModifierProcessor {

    private val hiddenRowsModifier = sheet.sheetModifiers<Sheet.Modifier.HideRows>()

    override fun requests(currentSheetId: Int) = hiddenRowsModifier?.let {
        it.rowsToHide.map { row ->
            request {
                updateSheetDimensionProperties {
                    fields = "hiddenByUser"
                    properties = dimensionProperties {
                        hiddenByUser = true
                    }
                    range = dimensionRange {
                        sheetId = currentSheetId
                        startIndex = row
                        endIndex = row + 1
                        dimension = "ROWS"
                    }
                }
            }
        }
    } ?: emptyList()

}

class HideColumnsProcessor(
    val sheet: Sheet
) : SheetModifierProcessor {

    private val hiddenRowsModifier = sheet.sheetModifiers<Sheet.Modifier.HideColumns>()

    override fun requests(currentSheetId: Int) = hiddenRowsModifier?.let {
        it.columnsToHide.map { column ->
            request {
                updateDimensionProperties = UpdateDimensionPropertiesRequest().apply {
                    fields = "hiddenByUser"
                    properties.hiddenByUser = true
                    range = dimensionRange {
                        sheetId = currentSheetId
                        startIndex = column
                        endIndex = column + 1
                        dimension = "COLUMNS"
                    }
                }
            }
        }
    } ?: emptyList()
}
