package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference

class Map(
    vararg valueOrRange: Type.Either<Type.Singular, Type.Range>,
    lambda: Lambda
) : Type.Formula.ReturnsAnys, Type.Formula by UseFormula(
    "MAP", *valueOrRange, lambda
)

interface HasMap : WorksWithFormulas, HasLambda {

    fun map(range: Type.Range, lambda: Lambda1) = Map(range.either(), lambda = lambda(lambda))
    fun map(firstRange: Type.Range, secondRange: Type.Range, lambda: Lambda2) =
        Map(firstRange.either(), secondRange.either(), lambda = lambda(lambda))
    fun map(firstRange: Type.Range, secondRange: Type.Range, thirdRange: Type.Range, lambda: Lambda3) =
        Map(firstRange.either(), secondRange.either(), thirdRange.either(), lambda = lambda(lambda))

    fun map(range: CellRangeReference, lambda: Lambda1) = map(range.type(), lambda)
    fun map(firstRange: CellRangeReference, secondRange: CellRangeReference, lambda: Lambda2) =
        map(firstRange.type(), secondRange.type(), lambda)
    fun map(firstRange: CellRangeReference, secondRange: CellRangeReference, thirdRange: CellRangeReference, lambda: Lambda3) =
        map(firstRange.type(), secondRange.type(), thirdRange.type(), lambda)

}