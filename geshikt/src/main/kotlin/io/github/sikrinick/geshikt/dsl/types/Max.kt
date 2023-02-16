package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference

class Max(
    vararg numbers: Type.Either<Type.Number, Type.Numbers>,
) : Type.Formula.ReturnsNumber, Type.Formula by UseFormula(
    "MAX", *numbers
)

interface HasMax : WorksWithFormulas {
    fun max(range: HasCellRangeReference) = max(range.reference)
    fun max(range: CellRangeReference) = max(range.type())
    fun max(vararg numbers: Type.Number) = Max(*numbers.map { it.either<Type.Number, Type.Numbers>() }.toTypedArray())
    fun max(type: Type.Numbers) = Max(type.either())
}
