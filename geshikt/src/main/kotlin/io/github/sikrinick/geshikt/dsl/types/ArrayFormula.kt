package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference

class ArrayFormula(
    range: Type.Either<Type.Formula, Type.Range>
) : Type.Formula.ReturnsAnys {
    override val name = "ARRAYFORMULA"
    override val arguments = arrayOf(range)
}

interface HasArrayFormula : WorksWithFormulas {
    fun arrayformula(range: HasCellRangeReference) = arrayformula(range.reference)
    fun arrayformula(range: CellRangeReference) =
        arrayformula(Type.RangeReference.Anys(range))
    fun arrayformula(invoke: VectorizedFormulas.() -> Type.Formula) =
        ArrayFormula(Type.Either(VectorizedFormulasEnv().invoke(), null))
    fun arrayformula(range: Type.Range) = ArrayFormula(Type.Either(null, range))
}

class ArrayExpression {

    infix fun isEqualTo(reference: CellRangeReference) =
        LogicalOperator.EqualTo to reference

    infix fun isNotEqualTo(reference: CellRangeReference) =
        LogicalOperator.NotEqualTo to reference
    infix fun isGreaterThan(reference: CellRangeReference) =
        LogicalOperator.GreaterThan to reference

    infix fun isGreaterThanOrEqualTo(reference: CellRangeReference) =
        LogicalOperator.GreaterThanOrEqualTo to reference

    infix fun isLessThan(reference: CellRangeReference) =
        LogicalOperator.LessThan to reference
    infix fun isLessThanOrEqualTo(reference: CellRangeReference) =
        LogicalOperator.LessThanOrEqualTo to reference

    private infix fun LogicalOperator.to(reference: CellRangeReference) =
        Type.CriterionRange(this, Type.RangeReference.Numbers(reference))
}

typealias RangeCriterion = (ArrayExpression) -> Type.CriterionRange


class VectorizedFormulasEnv : VectorizedFormulas
interface VectorizedFormulas : WorksWithFormulas,
    HasFormulas,
    HasArrayLiteral,

    // Math
    HasArrayCountIf,

    // Date
    HasArrayDateDif,

    // Filter
    HasArrayFilter,

    // Logical
    HasArrayIf,
    HasArrayIfError,

    // Operator
    HasArrayEq,
    HasArrayGte,
    HasArrayLt,

    // Text
    HasArrayRegexMatch,
    HasArrayToText,
    HasArrayTextJoin
{

    fun conditions(block: ArrayExpression.() -> Type.Booleans) =
        ArrayExpression().block()

    operator fun CellRangeReference.minus(reference: CellRangeReference) =
        ArrayMinus(Type.RangeReference.Numbers(this), Type.RangeReference.Numbers(reference))

}