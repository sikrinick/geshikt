package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

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

interface HasRegexMatch : WorksWithFormulas {
    fun regexMatch(reference: CellReference, pattern: String) =
        regexMatch(reference.type(), pattern)

    fun regexMatch(text: Type.Text, pattern: String) =
        RegexMatch(text, pattern.type())
}

interface HasArrayRegexMatch : WorksWithFormulas {
    fun regexMatch(reference: CellRangeReference, pattern: String) =
        regexMatch(reference.type(), pattern)

    fun regexMatch(texts: Type.Texts, pattern: String) =
        ArrayRegexMatch(texts.either(), pattern.type())
}