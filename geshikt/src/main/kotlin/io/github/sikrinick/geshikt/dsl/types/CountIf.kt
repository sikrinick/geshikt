package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference

class CountIf(
    range: Type.Range,
    criterion: Type.Criterion
) : Type.Formula.ReturnsNumber {
    override val name = "COUNTIF"
    override val arguments = arrayOf(range, criterion)
}

interface HasCountIf : WorksWithFormulas {
    fun countIf(cellRangeReference: CellRangeReference, criterion: Criterion) =
        CountIf(cellRangeReference.type(), Expression().let(criterion))
}

class ArrayCountIf(
    range: Type.Range,
    criteria: Type.Either<Type.Boolean, Type.Booleans> // Currently, only equals supported
) : Type.Formula.ReturnsNumbers, Type.Formula by UseFormula(
    "COUNTIF", range, criteria
)

interface HasArrayCountIf : WorksWithFormulas {

    fun countIf(range: HasCellRangeReference, criteria: HasCellRangeReference) =
        countIf(range.reference, criteria.reference)
    fun countIf(range: HasCellRangeReference, criteria: CellRangeReference) =
        countIf(range.reference, criteria)
    fun countIf(range: CellRangeReference, criteria: CellRangeReference) =
        countIf(range.type(), criteria.type())
    fun countIf(range: Type.Range, criteria: Type.Boolean) =
        ArrayCountIf(range, criteria.either())
    fun countIf(range: Type.Range, criteria: Type.Booleans) =
        ArrayCountIf(range, criteria.either())

}