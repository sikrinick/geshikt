package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference

class ArrayFilter(
    range: Type.Range,
    vararg conditions: Type.Either<Type.Boolean, Type.Booleans>
) : Type.Formula.ReturnsAnys, Type.Formula by UseFormula(
    "FILTER", range, *conditions
)

interface HasArrayFilter {
    fun filter(range: CellRangeReference, conditions: Type.Booleans) =
        ArrayFilter(Type.RangeReference.Anys(range), Type.Either(null, conditions))
}