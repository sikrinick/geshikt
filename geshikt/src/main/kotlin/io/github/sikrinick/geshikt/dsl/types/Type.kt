package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellReference as DslCellReference
import io.github.sikrinick.geshikt.dsl.values.Reference as DslReference
import kotlin.Boolean as KotlinBoolean
import kotlin.Number as KotlinNumber
import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import java.time.LocalDate

sealed interface Type {

    sealed interface Singular : Type
    sealed interface Range : Type

    sealed interface Comparable : Singular
    sealed interface Comparables : Range

    sealed interface Text : Singular
    sealed interface Number : Comparable
    sealed interface Date : Comparable
    sealed interface Boolean : Singular
    sealed interface Any : Text, Number, Date, Boolean

    sealed interface Texts : Range
    sealed interface Numbers : Comparables
    sealed interface Dates : Comparables
    sealed interface Booleans : Range
    sealed interface Anys : Texts, Numbers, Dates, Booleans

    //region Hardcoded
    sealed interface Hardcoded : Singular {
        data class Text(val text: String) : Hardcoded, Type.Text
        data class Number(val number: KotlinNumber) : Hardcoded, Type.Number
        data class Date(val date: LocalDate) : Hardcoded, Type.Date
        data class Boolean(val boolean: KotlinBoolean) : Hardcoded, Type.Boolean
    }
    //endregion

    //region Dynamic
    sealed interface Dynamic : Type
    sealed interface Reference : Dynamic {
        val reference: DslReference
    }
    sealed interface CellReference : Reference, Singular {
        data class Any(override val reference: DslCellReference) : Reference, Type.Any
        data class Text(override val reference: DslCellReference) : Reference, Type.Text
        data class Number(override val reference: DslCellReference) : Reference, Type.Number
        data class Date(override val reference: DslCellReference) : Reference, Type.Date
        data class Boolean(override val reference: DslCellReference) : Reference, Type.Boolean
    }

    sealed interface RangeReference : Reference, Range {
        data class Anys(override val reference: CellRangeReference) : RangeReference, Type.Anys
        data class Texts(override val reference: CellRangeReference) : RangeReference, Type.Texts
        data class Numbers(override val reference: CellRangeReference) : RangeReference, Type.Numbers
        data class Dates(override val reference: CellRangeReference) : RangeReference, Type.Dates
        data class Booleans(override val reference: CellRangeReference) : RangeReference, Type.Booleans
    }

    sealed interface Formula : Dynamic {
        val name: String
        val arguments: Array<out Type>

        sealed interface ReturnsSingular : Formula, Singular
        sealed interface ReturnsRange : Formula, Range

        sealed interface ReturnsAny : ReturnsSingular, Any
        sealed interface ReturnsText : ReturnsSingular, Text
        sealed interface ReturnsNumber : ReturnsSingular, Number
        sealed interface ReturnsDate : ReturnsSingular, Date
        sealed interface ReturnsBoolean : ReturnsSingular, Boolean

        sealed interface ReturnsAnys : ReturnsRange, Anys
        sealed interface ReturnsTexts : ReturnsRange, Texts
        sealed interface ReturnsNumbers : ReturnsRange, Numbers
        sealed interface ReturnsDates : ReturnsRange, Dates
        sealed interface ReturnsBooleans : ReturnsRange, Booleans
    }


    class ArrayLiteral(val rows: List<Row>) : Type.Range {
        class Row(val columns: List<Type>)
    }


    data class Criterion(
        val operator: LogicalOperator,
        val obj: Comparable
    ) : Dynamic, Boolean

    data class CriterionRange(
        val operator: LogicalOperator,
        val objs: Comparables
    ) : Dynamic, Booleans
    //endregion

    // Utility
    data class Either<out L : Type, out R : Type>(
        val left: L? = null,
        val right: R? = null
    ) : Type
}
