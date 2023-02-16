package io.github.sikrinick.geshikt.dsl.types


class IfError(
    dynamic: Type.Dynamic,
    onError: Type
) : Type.Formula.ReturnsAny, Type.Formula by UseFormula(
    "IFERROR", dynamic, onError
)

class ArrayIfError(
    dynamic: Type.Dynamic,
    onError: Type
) : Type.Formula.ReturnsAnys, Type.Formula by UseFormula(
    "IFERROR", dynamic, onError
)

interface HasIfError {
    fun iferror(type: Type.Dynamic, onError: String) =
        IfError(type, Type.Hardcoded.Text(onError))
}

interface HasArrayIfError {

}