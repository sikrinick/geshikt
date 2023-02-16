package io.github.sikrinick.geshikt.api

import com.google.api.services.sheets.v4.model.BooleanCondition
import io.github.sikrinick.geshikt.dsl.types.LogicalOperator
import io.github.sikrinick.geshikt.dsl.types.Type
import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.runtime.component.BooleanRuleCondition
import io.github.sikrinick.geshikt.runtime.component.cell.TypeProcessor

class BooleanConditionProducer(
    private val typeProcessor: TypeProcessor = TypeProcessor()
) {

    fun oneOfRange(reference: CellRangeReference) = BooleanRuleCondition.OneOfRange(
        userEnteredRange = "=${typeProcessor.process(Type.RangeReference.Anys(reference))}"
    ).toBooleanRule()

    fun parse(condition: Type.Criterion): BooleanCondition {
        val value = typeProcessor.process(condition.obj)
        return when(condition.operator) {
            LogicalOperator.EqualTo -> BooleanRuleCondition.NumberEq(value)
            LogicalOperator.NotEqualTo -> BooleanRuleCondition.NumberNotEq(value)
                LogicalOperator.GreaterThan -> BooleanRuleCondition.NumberGreater(value)
            LogicalOperator.GreaterThanOrEqualTo -> BooleanRuleCondition.NumberGreaterThanEq(value)
            LogicalOperator.LessThan -> BooleanRuleCondition.NumberLess(value)
            LogicalOperator.LessThanOrEqualTo -> BooleanRuleCondition.NumberLessThanEq(value)
        }.toBooleanRule()
    }

}