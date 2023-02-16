package io.github.sikrinick.geshikt.dsl.types

class Char(
    private val tableNumber: Type.Number
) : Type.Formula.ReturnsText, Type.Formula by UseFormula(
    "CHAR", tableNumber
)

interface HasChar {

    fun char(tableNumber: Number) = Char(Type.Hardcoded.Number(tableNumber))

}