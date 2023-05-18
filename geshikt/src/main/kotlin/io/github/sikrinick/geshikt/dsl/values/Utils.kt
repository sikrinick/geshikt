package io.github.sikrinick.geshikt.dsl.values

import io.github.sikrinick.geshikt.dsl.component.layout.Position

fun PositionedCellRangeReference.toPositionedCellReferenceList() = with(area) {
    (startX until endX).flatMap { x ->
        (startY until endY).map { y ->
            Position(title, x, y)
        }
    }.map {
        PositionedCellReference(it)
    }
}

fun PositionedCellRangeReference.first() = toPositionedCellReferenceList().first()