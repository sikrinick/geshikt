package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference

class CountA(
    range: Type.Range,
) : Type.Formula.ReturnsNumber, Type.Formula by UseFormula(
    "COUNTA", range
)

interface HasCountA {
    fun counta(range: HasCellRangeReference) =
        counta(range.reference)
    fun counta(range: CellRangeReference) =
        counta(Type.RangeReference.Anys(range))
    fun counta(type: Type.Range) = CountA(type)
}
