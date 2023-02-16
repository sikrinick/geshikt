package io.github.sikrinick.geshikt.runtime.component.cell.reference

import io.github.sikrinick.geshikt.dsl.SheetTitle
import io.github.sikrinick.geshikt.dsl.component.layout.HasPosition
import io.github.sikrinick.geshikt.dsl.utils.A1Notation

class PositionMapper {

    fun toA1Notation(position: HasPosition) = "${position.title.sheet()}${position.column()}${position.row()}"

    private fun SheetTitle.sheet() = if (value.isNotBlank()) "'${value}'!" else ""
    private fun HasPosition.column() = A1Notation.column(this)
    private fun HasPosition.row() = A1Notation.row(this)
}
