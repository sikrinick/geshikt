package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

class Lt(
    value1: Type,
    value2: Type
) : Type.Formula.ReturnsBoolean, Type.Formula by UseFormula(
    "LT", value1, value2
)

class ArrayLt(
    value1: Type,
    value2: Type
) : Type.Formula.ReturnsBooleans, Type.Formula by UseFormula(
    "LT", value1, value2
)

interface HasLt : WorksWithFormulas {

    infix fun Number.isLessThan(number: Number) = Lt(type(), number.type())
    infix fun CellReference.isLessThan(number: Number) = Lt(type(), number.type())
    infix fun Number.isLessThan(reference: CellReference) = Lt(type(), reference.type())
    infix fun CellReference.isLessThan(reference: CellReference) = Lt(type(), reference.type())

}

interface HasArrayLt : WorksWithFormulas {

    infix fun CellRangeReference.isLessThan(number: Number) = ArrayLt(type(), number.type())
    infix fun CellRangeReference.isLessThan(reference: CellReference) = ArrayLt(type(), reference.type())
    infix fun CellRangeReference.isLessThan(references: CellRangeReference) = ArrayLt(type(), references.type())
    infix fun Type.Range.isLessThan(number: Number) = ArrayLt(this, number.type())
    infix fun Type.Range.isLessThan(references: CellRangeReference) = ArrayLt(this, references.type())
}