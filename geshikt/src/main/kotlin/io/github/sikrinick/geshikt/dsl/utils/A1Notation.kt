package io.github.sikrinick.geshikt.dsl.utils

import io.github.sikrinick.geshikt.dsl.component.layout.HasPosition

object A1Notation {

    fun column(hasPosition: HasPosition) = column(hasPosition.startX)
    fun row(hasPosition: HasPosition) = row(hasPosition.startY)

    fun column(col: Int) = col.toHexavigesimal()
    fun row(row: Int) = (row + 1).toString()

    private fun Int.toHexavigesimal(start: Boolean = true): String = when(this) {
        0 -> if (start) "A" else ""
        else -> (this / 26).toHexavigesimal(false) + ('A' + (this % 26))
    }
}