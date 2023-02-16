package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference
import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference

class Sum(
    vararg numbers: Type.Either<Type.Number, Type.Numbers>
) : Type.Formula.ReturnsNumber, Type.Formula by UseFormula(
    "SUM", *numbers
)

interface HasSum : WorksWithFormulas {

    operator fun CellReference.plus(reference: CellReference) =
        sum(Type.CellReference.Number(this), Type.CellReference.Number(reference))

    operator fun Type.Number.plus(reference: Type.Number) =
        sum(this, reference)

    operator fun Type.Number.plus(number: Number) =
        sum(this, Type.Hardcoded.Number(number))

    fun sum(range: HasCellRangeReference) = sum(range.reference)
    fun sum(range: CellRangeReference) = sum(Type.RangeReference.Numbers(range))
    fun sum(vararg refs: CellReference) = sum(*refs.map { Type.CellReference.Number(it) }.toTypedArray())

    fun sum(vararg numbers: Type.Number) = Sum(*numbers.map { it.either<Type.Number, Type.Numbers>() }.toTypedArray())
    fun sum(type: Type.Numbers) = Sum(type.either())
}
