package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

class ToText(
    any: Type.Singular,
    pattern: Type.Hardcoded.Text
) : Type.Formula.ReturnsText {
    override val name = "TEXT"
    override val arguments = arrayOf(any, pattern)
}

class ArrayToText(
    any: Type.Either<Type.Singular, Type.Range>,
    pattern: Type.Hardcoded.Text
) : Type.Formula.ReturnsTexts {
    override val name = "TEXT"
    override val arguments = arrayOf(any, pattern)
}

interface HasToText : WorksWithFormulas {
    fun text(reference: CellReference, pattern: String) =
        ToText(reference.type(), pattern.type())
}

interface HasArrayToText : WorksWithFormulas {
    fun text(reference: CellRangeReference, pattern: String) =
        ArrayToText(reference.type().either(), pattern.type())
}