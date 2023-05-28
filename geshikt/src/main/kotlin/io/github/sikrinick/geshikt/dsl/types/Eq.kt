package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

class Eq(
    value1: Type,
    value2: Type.Singular
) : Type.Formula.ReturnsBoolean, Type.Formula by UseFormula(
    "EQ", value1, value2
)

class ArrayEq(
    value1: Type,
    value2: Type
) : Type.Formula.ReturnsBooleans, Type.Formula by UseFormula(
    "EQ", value1, value2
)

interface HasEq : WorksWithFormulas {

    infix fun Number.isEqualTo(number: Number) = Eq(type(), number.type())
    infix fun CellReference.isEqualTo(number: Number) = Eq(type(), number.type())
    infix fun CellRangeReference.isEqualTo(number: Number) = Eq(type(), number.type())
    infix fun Number.isEqualTo(reference: CellReference) = Eq(type(), reference.type())
    infix fun CellReference.isEqualTo(reference: CellReference) = Eq(type(), reference.type())
    infix fun CellRangeReference.isEqualTo(reference: CellReference) = Eq(type(), reference.type())

}

interface HasArrayEq : WorksWithFormulas {
    infix fun CellRangeReference.isEqualTo(references: CellRangeReference) = ArrayEq(type(), references.type())
}