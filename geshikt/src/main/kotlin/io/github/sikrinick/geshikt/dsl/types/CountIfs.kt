package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference

class CountIfs(
    vararg criteria: Pair<Type.Range, Type.Criterion>,
) : Type.Formula.ReturnsNumber, Type.Formula by UseFormula(
    "COUNTIFS", *criteria.flatMap { listOf(it.first, it.second) }.toTypedArray()
)

class ArrayCountIfs(
    range: Type.Range,
    criteria: Type.Either<Type.Boolean, Type.Booleans> // Currently, only equals supported
) : Type.Formula.ReturnsNumbers, Type.Formula by UseFormula(
    "COUNTIFS", range, criteria
)

interface HasCountIfs : WorksWithFormulas {

    fun countIfs(vararg criteria: Pair<CellRangeReference, Criterion>) =
        CountIfs(
            *criteria
                .map { (lhs, rhs) -> lhs.type() to Expression().let(rhs) }
                .toTypedArray()
        )
}
