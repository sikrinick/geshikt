package io.github.sikrinick.geshikt.dsl.types

class Join(
    val delimiter: Type.Text,
    vararg val valueOrArray: Type.Either<Type.Text, Type.Texts>,
) : Type.Formula.ReturnsText, Type.Formula by UseFormula(
    "JOIN", delimiter, *valueOrArray
)

interface HasJoin {

    fun join(delimiter: String, vararg values: String) =
        join(
            delimiter,
            *values.map { Type.Hardcoded.Text(it) }.toTypedArray()
        )

    fun join(delimiter: String, vararg values: Type.Text) =
        Join(
            Type.Hardcoded.Text(delimiter),
            *values.map { Type.Either(it, null) }.toTypedArray()
        )
}
