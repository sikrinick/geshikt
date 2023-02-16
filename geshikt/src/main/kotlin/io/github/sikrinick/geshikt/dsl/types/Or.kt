package io.github.sikrinick.geshikt.dsl.types

class Or(
    vararg val logicalExpression: Type.Boolean
) : Type.Formula.ReturnsBoolean, Type.Formula by UseFormula(
    "OR", *logicalExpression
)

interface HasOr {

    fun or(vararg logicalExpressions: Type.Boolean) = Or(*logicalExpressions)
    infix fun Type.Boolean.or(logicalExpression: Type.Boolean) = Or(this, logicalExpression)

}