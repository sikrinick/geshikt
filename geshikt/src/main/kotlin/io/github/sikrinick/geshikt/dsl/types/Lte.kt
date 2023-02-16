package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

class Lte(
    value1: Type,
    value2: Type
) : Type.Formula.ReturnsBoolean, Type.Formula by UseFormula(
    "LTE", value1, value2
)

interface HasLte : WorksWithFormulas {

    infix fun Number.isLessThanOrEqualsTo(number: Number) = Lte(type(), number.type())
    infix fun CellReference.isLessThanOrEqualsTo(number: Number) = Lte(type(), number.type())
    infix fun CellRangeReference.isLessThanOrEqualsTo(number: Number) = Lte(type(), number.type())
    infix fun Number.isLessThanOrEqualsTo(reference: CellReference) = Lte(type(), reference.type())
    infix fun CellReference.isLessThanOrEqualsTo(reference: CellReference) = Lte(type(), reference.type())
    infix fun CellRangeReference.isLessThanOrEqualsTo(reference: CellReference) = Lte(type(), reference.type())

}