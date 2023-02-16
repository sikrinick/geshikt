package io.github.sikrinick.geshikt.dsl.component.layout

import io.github.sikrinick.geshikt.dsl.SheetTitle

interface HasPosition {
    val title: SheetTitle
    val startX: Int
    val startY: Int
}

data class Position(
    override val title: SheetTitle,
    override val startX: Int,
    override val startY: Int
) : HasPosition