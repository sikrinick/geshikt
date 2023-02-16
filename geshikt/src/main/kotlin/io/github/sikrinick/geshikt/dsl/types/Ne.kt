package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

class Ne(
    value1: Type,
    value2: Type
) : Type.Formula.ReturnsBoolean, Type.Formula by UseFormula(
    "NE", value1, value2
)

interface HasNe : WorksWithFormulas {

    infix fun Number.isNotEqualTo(number: Number) = Ne(type(), number.type())
    infix fun CellReference.isNotEqualTo(number: Number) = Ne(type(), number.type())
    infix fun CellRangeReference.isNotEqualTo(number: Number) = Ne(type(), number.type())
    infix fun Number.isNotEqualTo(reference: CellReference) = Ne(type(), reference.type())
    infix fun CellReference.isNotEqualTo(reference: CellReference) = Ne(type(), reference.type())
    infix fun CellRangeReference.isNotEqualTo(reference: CellReference) = Ne(type(), reference.type())

}
