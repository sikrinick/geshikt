package io.github.sikrinick.geshikt.runtime.component.cell.reference

import io.github.sikrinick.geshikt.dsl.SheetTitle
import io.github.sikrinick.geshikt.dsl.component.layout.Area
import io.github.sikrinick.geshikt.dsl.component.layout.Position

class AreaMapper(
    private val positionMapper: PositionMapper = PositionMapper()
) {
    fun toA1Notation(area: Area): String {
        val start = positionMapper.toA1Notation(Position(emptyTitle, area.startX, area.startY))
        val end = positionMapper.toA1Notation(Position(emptyTitle, area.endX - 1, area.endY - 1))
        return "${start}:${end}"
    }

    private companion object {
        private val emptyTitle = SheetTitle("")
    }
}