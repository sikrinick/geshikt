package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference

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

interface HasArrayToText {
    fun text(reference: CellRangeReference, pattern: String) =
        ArrayToText(
            Type.Either(null, Type.RangeReference.Anys(reference)),
            Type.Hardcoded.Text(pattern)
        )
}