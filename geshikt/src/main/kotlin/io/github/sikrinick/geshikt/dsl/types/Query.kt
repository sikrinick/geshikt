package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

class Query(
    data: Type.Range,
    query: Type.Text
) : Type.Formula.ReturnsAnys, Type.Formula by UseFormula(
    "QUERY", data, query
)

interface HasQuery : WorksWithFormulas {

    fun query(data: CellRangeReference, query: CellReference) =
        query(data.type(), query)

    fun query(data: Type.Range, query: CellReference) =
        Query(data, query.type())

}