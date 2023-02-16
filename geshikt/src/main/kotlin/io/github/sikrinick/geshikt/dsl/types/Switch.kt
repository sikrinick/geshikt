package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellReference
import io.github.sikrinick.geshikt.dsl.values.HasCellReference

class Switch(
    val expression: Type,
    vararg val cases: Pair<Type.Singular, Type.Singular>
) : Type.Formula.ReturnsText, Type.Formula by UseFormula(
    "SWITCH", expression, *cases.flatMap { listOf(it.first, it.second) }.toTypedArray()
)

interface HasSwitch : WorksWithFormulas {

    fun switch(expression: HasCellReference, vararg cases: Pair<Type.Singular, Type.Singular>) =
        switch(expression.reference, *cases)

    fun switch(expression: CellReference, vararg cases: Pair<Type.Singular, Type.Singular>) =
        switch(expression.type(), *cases)

    fun switch(expression: Type, vararg cases: Pair<Type.Singular, Type.Singular>) =
        Switch(expression, *cases)
}
