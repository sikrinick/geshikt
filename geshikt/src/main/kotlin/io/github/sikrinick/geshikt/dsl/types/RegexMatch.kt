package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference

class RegexMatch(
    val text: Type.Text,
    val pattern: Type.Hardcoded.Text
) : Type.Formula.ReturnsBoolean {
    override val name = "REGEXMATCH"
    override val arguments = arrayOf(text, pattern)
}

class ArrayRegexMatch(
    val text: Type.Either<Type.Text, Type.Texts>,
    val pattern: Type.Hardcoded.Text
) : Type.Formula.ReturnsBooleans {
    override val name = "REGEXMATCH"
    override val arguments = arrayOf(text, pattern)
}

interface HasArrayRegexMatch {
    fun regexMatch(reference: CellRangeReference, pattern: String) =
        regexMatch(
            Type.RangeReference.Texts(reference),
            pattern
        )
    fun regexMatch(texts: Type.Texts, pattern: String) =
        ArrayRegexMatch(
            Type.Either(null, texts),
            Type.Hardcoded.Text(pattern)
        )
}