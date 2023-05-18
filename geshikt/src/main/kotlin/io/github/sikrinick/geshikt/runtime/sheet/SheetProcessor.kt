package io.github.sikrinick.geshikt.runtime.sheet

import com.google.api.services.sheets.v4.model.Sheet as GoogleSheet
import io.github.sikrinick.geshikt.dsl.Sheet as UISheet
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse
import com.google.api.services.sheets.v4.model.Request
import io.github.sikrinick.geshikt.ProcessingContext
import io.github.sikrinick.geshikt.api.addSheet
import io.github.sikrinick.geshikt.api.gridProperties
import io.github.sikrinick.geshikt.api.request
import io.github.sikrinick.geshikt.api.sheetProperties
import io.github.sikrinick.geshikt.api.updateSheetProperties
import io.github.sikrinick.geshikt.dsl.Sheet
import io.github.sikrinick.geshikt.runtime.component.Component
import io.github.sikrinick.geshikt.runtime.component.Container
import java.util.TreeMap

internal class SheetProcessor(
    private val uiSheet: UISheet,
    private val context: ProcessingContext,
    sheets: List<GoogleSheet>,
    private val sheetPropertiesProcessors: List<SheetPropertiesProcessor> = listOf(
        SheetGridPropertiesProcessor(uiSheet),
        HiddenSheetModifierProcessor(uiSheet),
    ),
    private val sheetModifierProcessors: List<SheetModifierProcessor> = listOf(
        HideRowsProcessor(uiSheet),
        HideColumnsProcessor(uiSheet)
    )
) {

    private val sheetTitle = uiSheet.title.value
    private val currentSheet = sheets.firstOrNull { it.properties.title == sheetTitle }

    val createSheet = listOfNotNull(sheetTitle.takeUnless { currentSheet != null }?.let {
        request {
            addSheet {
                properties = sheetProperties {
                    title = it
                    gridProperties = gridProperties {
                        rowCount = context.maxRowCount
                    }
                }
            }
        }
    })

    fun getId(createSheetResponse: BatchUpdateSpreadsheetResponse?): Int =
        currentSheet?.properties?.sheetId
            ?: createSheetResponse?.replies?.first()?.addSheet?.properties?.sheetId
            ?: throw RuntimeException("Sheet Id was not found")

    fun updateSheet(currentSheetId: Int) = (
        (
            sheetPropertiesProcessors
                .map(SheetPropertiesProcessor::field)
                .filter { it.isNotBlank() }
                .takeIf { it.isNotEmpty() }
                ?.let { modifierFields ->
                    request {
                        updateSheetProperties {
                            properties = sheetProperties {
                                sheetId = currentSheetId
                                sheetPropertiesProcessors.map(SheetPropertiesProcessor::change).forEach { it() }
                                fields = modifierFields.joinToString(",")
                            }
                        }
                    }
                }
                ?.let { listOf(it) }
                ?: emptyList()
        )
        + sheetModifierProcessors.flatMap { it.requests(currentSheetId) }
        + Container(Component(currentSheetId, uiSheet), uiSheet).requests
    ).order()

    private fun List<Request>.order() =
        groupByTo(
            destination = TreeMap(RequestType.highPriorityFirst),
            keySelector = {
                when {
                    it.deleteNamedRange != null -> RequestType.DeleteNamedRange
                    it.addNamedRange != null -> RequestType.AddNamedRange
                    it.updateBorders != null -> RequestType.UpdateBorders
                    it.updateDimensionProperties != null -> RequestType.UpdateDimensionProperties
                    else -> RequestType.Other
                }
            },
            valueTransform = { it }
        ).values

    private enum class RequestType(val priority: Int) {
        DeleteNamedRange(1),
        AddNamedRange(2),

        Other(50),

        UpdateBorders(100),
        UpdateDimensionProperties(101);

        companion object {
            val highPriorityFirst = Comparator<RequestType> { a, b -> a.priority - b.priority }
        }
    }
}
