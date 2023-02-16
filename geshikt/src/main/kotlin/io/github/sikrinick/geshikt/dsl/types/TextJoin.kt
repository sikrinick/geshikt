package io.github.sikrinick.geshikt.dsl.types

class ArrayTextJoin(
    val delimiter: Type.Text,
    val ignoreEmpty: Type.Boolean,
    val text1: Type.Either<Type.Text, Type.Texts>,
    vararg val text2: Type.Text
) : Type.Formula.ReturnsTexts, Type.Formula by UseFormula(
    "TEXTJOIN", delimiter, ignoreEmpty, text1, *text2
)

interface HasArrayTextJoin {
    fun textjoin(delimiter: String, ignoreEmpty: Boolean, text1: Type.Texts, vararg text2: Type.Text) =
        ArrayTextJoin(
            Type.Hardcoded.Text(delimiter),
            Type.Hardcoded.Boolean(ignoreEmpty),
            Type.Either(null, text1),
            *text2
        )

}