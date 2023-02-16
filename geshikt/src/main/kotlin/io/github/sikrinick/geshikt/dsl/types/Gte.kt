package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

class Gte(
    value1: Type.Singular,
    value2: Type.Singular
) : Type.Formula.ReturnsBoolean, Type.Formula by UseFormula(
    "GTE", value1, value2
)

class ArrayGte(
    value1: Type,
    value2: Type
) : Type.Formula.ReturnsBooleans, Type.Formula by UseFormula(
    "GTE", value1, value2
)

interface HasGte : WorksWithFormulas {

    infix fun Number.isGreaterThanOrEqualsTo(number: Number) = Gte(type(), number.type())
    infix fun CellReference.isGreaterThanOrEqualsTo(number: Number) = Gte(type(), number.type())
    infix fun Number.isGreaterThanOrEqualsTo(reference: CellReference) = Gte(type(), reference.type())
    infix fun CellReference.isGreaterThanOrEqualsTo(reference: CellReference) = Gte(type(), reference.type())

}

interface HasArrayGte : WorksWithFormulas {

    infix fun CellRangeReference.isGreaterThanOrEqualsTo(number: Number) = ArrayGte(type(), number.type())
    infix fun CellRangeReference.isGreaterThanOrEqualsTo(reference: CellReference) = ArrayGte(type(), reference.type())
}
