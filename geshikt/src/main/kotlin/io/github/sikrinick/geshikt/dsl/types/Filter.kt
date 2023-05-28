package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference


class Filter(
    range: Type.Range,
    vararg conditions: Type.Boolean
) : Type.Formula.ReturnsAnys, Type.Formula by UseFormula(
    "FILTER", range, *conditions
)

class ArrayFilter(
    range: Type.Range,
    vararg conditions: Type.Either<Type.Boolean, Type.Booleans>
) : Type.Formula.ReturnsAnys, Type.Formula by UseFormula(
    "FILTER", range, *conditions
)

interface HasFilter {
    fun filter(range: CellRangeReference, vararg conditions: Type.Boolean) =
        Filter(Type.RangeReference.Anys(range), *conditions)
}

interface HasArrayFilter {
    fun filter(range: CellRangeReference, vararg conditions: Type.Booleans) =
        ArrayFilter(Type.RangeReference.Anys(range), *conditions.map { Type.Either(null, it) }.toTypedArray())
}