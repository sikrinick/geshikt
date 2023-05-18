package io.github.sikrinick.geshikt.runtime.component

import com.google.api.services.sheets.v4.model.GridCoordinate
import com.google.api.services.sheets.v4.model.GridRange
import io.github.sikrinick.geshikt.api.coordinate
import io.github.sikrinick.geshikt.api.gridRange
import io.github.sikrinick.geshikt.dsl.component.layout.Area

interface Grid {
    val range: GridRange
    val coordinate: GridCoordinate
}

class AreaProcessor(
    private val currentSheetId: Int,
    private val area: Area
) : Grid {
    override val range = gridRange {
        sheetId = currentSheetId
        startRowIndex = area.startY
        startColumnIndex = area.startX
        endRowIndex = area.endY
        endColumnIndex = area.endX
    }
    override val coordinate = coordinate {
        sheetId = currentSheetId
        rowIndex = area.startY
        columnIndex = area.startX
    }
}