package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellReference

class Expression {

    infix fun isEqualTo(number: Number) = LogicalOperator.EqualTo to number
    infix fun isNotEqualTo(number: Number) = LogicalOperator.NotEqualTo to number
    infix fun isGreaterThan(number: Number) = LogicalOperator.GreaterThan to number
    infix fun isGreaterThanOrEqualTo(number: Number) = LogicalOperator.GreaterThanOrEqualTo to number
    infix fun isLessThan(number: Number) = LogicalOperator.LessThan to number
    infix fun isLessThanOrEqualTo(number: Number) = LogicalOperator.LessThanOrEqualTo to number

    infix fun isEqualTo(reference: CellReference) = LogicalOperator.EqualTo to reference
    infix fun isNotEqualTo(reference: CellReference) = LogicalOperator.NotEqualTo to reference
    infix fun isGreaterThan(reference: CellReference) = LogicalOperator.GreaterThan to reference
    infix fun isGreaterThanOrEqualTo(reference: CellReference) = LogicalOperator.GreaterThanOrEqualTo to reference
    infix fun isLessThan(reference: CellReference) = LogicalOperator.LessThan to reference
    infix fun isLessThanOrEqualTo(reference: CellReference) = LogicalOperator.LessThanOrEqualTo to reference

    private infix fun LogicalOperator.to(number: Number) = this to Type.Hardcoded.Number(number)
    private infix fun LogicalOperator.to(reference: CellReference) = this to Type.CellReference.Number(reference)

    private infix fun LogicalOperator.to(comparable: Type.Comparable) =
        Type.Criterion(this, comparable)
}

typealias Criterion = (Expression) -> Type.Criterion

class FormulasEnv : HasFormulas
interface HasFormulas :
    WorksWithFormulas,
    HasArrayLiteral,

    // Array
    HasMap,

    // Date
    HasDateDif,
    HasToday,

    // Filter
    HasFilter,

    // Google
    HasArrayFormula,
    HasQuery,

    // Info
    HasIsBlank,

    // Logical
    HasAnd,
    HasIf,
    HasIfError,
    HasLambda,
    HasOr,
    HasSwitch,

    // Lookup
    HasIndex,

    // Math
    HasCountIf,
    HasCountIfs,
    HasSum,

    // Operator
    HasEq,
    HasGt,
    HasGte,
    HasLt,
    HasLte,
    HasMinus,
    HasNe,

    // Statistical
    HasCountA,
    HasMax,

    // Text
    HasChar,
    HasJoin,
    HasRegexMatch,
    HasToText,
    HasTextJoin