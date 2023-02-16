package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellReference

class IsBlank(
    val value: Type.Text
) : Type.Formula.ReturnsBoolean, Type.Formula by UseFormula(
    "ISBLANK", value
)

interface HasIsBlank {
    fun isblank(value: String) = isblank(Type.Hardcoded.Text(value))
    fun isblank(value: CellReference) = isblank(Type.CellReference.Text(value))
    fun isblank(value: Type.Text) = IsBlank(value)
}