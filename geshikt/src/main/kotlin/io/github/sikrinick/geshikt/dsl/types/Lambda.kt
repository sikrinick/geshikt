package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.NamedCellReference

class Lambda(
    vararg arguments: Type,
    formula: Type.Formula
) : Type.Formula.ReturnsAnys, Type.Formula by UseFormula(
    "LAMBDA", *arguments, formula
)

typealias Lambda0 = () -> Type.Formula
typealias Lambda1 = (Type) -> Type.Formula
typealias Lambda2 = (Type, Type) -> Type.Formula
typealias Lambda3 = (Type, Type, Type) -> Type.Formula
typealias Lambda4 = (Type, Type, Type, Type) -> Type.Formula
interface HasLambda : WorksWithFormulas {

    fun lambda(formula: Lambda0) = Lambda(formula = formula())
    fun lambda(formula: Lambda1) = Lambda(
        "X".arg(),
        formula = formula("X".arg())
    )
    fun lambda(formula: Lambda2) = Lambda(
        "X".arg(), "Y".arg(),
        formula = formula("X".arg(), "Y".arg())
    )
    fun lambda(formula: Lambda3) = Lambda(
        "A".arg(), "B".arg(), "C".arg(),
        formula = formula("A".arg(), "B".arg(), "C".arg())
    )

    private fun String.arg() = NamedCellReference(this).type()

}