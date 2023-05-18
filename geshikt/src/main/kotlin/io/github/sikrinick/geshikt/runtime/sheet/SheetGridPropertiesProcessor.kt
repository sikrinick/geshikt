package io.github.sikrinick.geshikt.runtime.sheet

import com.google.api.services.sheets.v4.model.SheetProperties
import io.github.sikrinick.geshikt.api.gridProperties
import io.github.sikrinick.geshikt.dsl.Sheet
import io.github.sikrinick.geshikt.dsl.invoke

class SheetGridPropertiesProcessor(
    val sheet: Sheet
) : SheetPropertiesProcessor {

    private val hideGridModifier = sheet.sheetModifiers<Sheet.Modifier.HideGrid>()

    private val frozenRowsModifier = sheet.sheetModifiers<Sheet.Modifier.FrozenRows>()
    private val frozenColumnsModifier = sheet.sheetModifiers<Sheet.Modifier.FrozenColumns>()

    private val hideEmptyRowsModifier = sheet.sheetModifiers<Sheet.Modifier.HideEmptyRows>()
    private val hideEmptyColumnsModifier = sheet.sheetModifiers<Sheet.Modifier.HideEmptyColumns>()

    override val field = listOfNotNull(
        hideGridModifier?.let { "gridProperties.hideGridlines" },

        frozenRowsModifier?.let { "gridProperties.frozenRowCount" },
        frozenColumnsModifier?.let { "gridProperties.frozenColumnCount" },

        hideEmptyRowsModifier?.let { "gridProperties.rowCount" },
        hideEmptyColumnsModifier?.let { "gridProperties.columnCount" },
    ).joinToString(",")

    override val change: SheetProperties.() -> Unit = {
        gridProperties = gridProperties {
            hideGridModifier?.let { hideGridlines = it.hideGrid }

            frozenRowsModifier?.let { frozenRowCount = it.count }
            frozenColumnsModifier?.let { frozenColumnCount = it.count }

            hideEmptyRowsModifier?.let { rowCount = sheet.height }
            hideEmptyColumnsModifier?.let { columnCount = sheet.width }
        }
    }
}