package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference

class Index(
    reference: Type.RangeReference,
    row: Type.Hardcoded.Number,
    column: Type.Hardcoded.Number
) : Type.Formula.ReturnsAny {
    override val name = "IF"
    override val arguments = arrayOf(reference, row, column)
}

interface HasIndex {

    fun index(hasCellRangeReference: HasCellRangeReference, row: Int, column: Int) =
        index(hasCellRangeReference.reference, row, column)
    fun index(rangeReference: CellRangeReference, row: Int, column: Int) =
        Index(Type.RangeReference.Anys(rangeReference), Type.Hardcoded.Number(row), Type.Hardcoded.Number(column))
}