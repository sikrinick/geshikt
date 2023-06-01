package io.github.sikrinick.geshikt.dsl.types

class And(
    vararg val logicalExpression: Type.Boolean
) : Type.Formula.ReturnsBoolean, Type.Formula by UseFormula(
    "AND", *logicalExpression
)

interface HasAnd {

    infix fun Type.Boolean.and(logicalExpression: Type.Boolean) = and(this, logicalExpression)
    fun and(vararg logicalExpressions: Type.Boolean) = And(*logicalExpressions)

}