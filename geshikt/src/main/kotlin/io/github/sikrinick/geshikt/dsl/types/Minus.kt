package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellReference

class Minus(
    lhs: Type.Number,
    rhs: Type.Number
) : Type.Formula.ReturnsNumber, Type.Formula by UseFormula(
    "MINUS", lhs, rhs
)

class ArrayMinus(
    lhs: Type.RangeReference.Numbers,
    rhs: Type.RangeReference.Numbers
) : Type.Formula.ReturnsNumbers, Type.Formula by UseFormula(
    "MINUS", lhs, rhs
)

interface HasMinus {

    operator fun CellReference.minus(reference: CellReference) =
        Minus(Type.CellReference.Number(this), Type.CellReference.Number(reference))

}

interface HasArrayMinus {

}
