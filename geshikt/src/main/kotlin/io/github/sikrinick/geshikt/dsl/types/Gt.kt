package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

class Gt(
    value1: Type,
    value2: Type
) : Type.Formula.ReturnsBoolean, Type.Formula by UseFormula(
    "GT", value1, value2
)

interface HasGt : WorksWithFormulas {

    infix fun Number.isGreaterThan(number: Number) = Gt(type(), number.type())
    infix fun CellReference.isGreaterThan(number: Number) = Gt(type(), number.type())
    infix fun CellRangeReference.isGreaterThan(number: Number) = Gt(type(), number.type())
    infix fun Number.isGreaterThan(reference: CellReference) = Gt(type(), reference.type())
    infix fun CellReference.isGreaterThan(reference: CellReference) = Gt(type(), reference.type())
    infix fun CellRangeReference.isGreaterThan(reference: CellReference) = Gt(type(), reference.type())

}