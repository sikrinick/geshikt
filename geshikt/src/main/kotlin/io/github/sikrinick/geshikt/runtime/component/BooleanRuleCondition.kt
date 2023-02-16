package io.github.sikrinick.geshikt.runtime.component

import com.google.api.services.sheets.v4.model.BooleanCondition
import com.google.api.services.sheets.v4.model.ConditionValue
import io.github.sikrinick.geshikt.api.booleanCondition

sealed interface BooleanRuleCondition {

    fun String.asValues() = listOf(ConditionValue().apply { userEnteredValue = this@asValues })
    fun toBooleanRule(): BooleanCondition

    data class NumberGreater(val userEnteredNumber: String) : BooleanRuleCondition {
        override fun toBooleanRule() = booleanCondition {
            type = "NUMBER_GREATER"
            setValues(userEnteredNumber.asValues())
        }
    }
    data class NumberGreaterThanEq(val userEnteredNumber: String) : BooleanRuleCondition {
        override fun toBooleanRule() = booleanCondition {
            type = "NUMBER_GREATER_THAN_EQ"
            setValues(userEnteredNumber.asValues())
        }
    }
    data class NumberLess(val userEnteredNumber: String) : BooleanRuleCondition {
        override fun toBooleanRule() = booleanCondition {
            type = "NUMBER_LESS"
            setValues(userEnteredNumber.asValues())
        }
    }
    data class NumberLessThanEq(val userEnteredNumber: String) : BooleanRuleCondition {
        override fun toBooleanRule() = booleanCondition {
            type = "NUMBER_LESS_THAN_EQ"
            setValues(userEnteredNumber.asValues())
        }
    }
    data class NumberEq(val userEnteredNumber: String) : BooleanRuleCondition {
        override fun toBooleanRule() = booleanCondition {
            type = "NUMBER_EQ"
            setValues(userEnteredNumber.asValues())
        }
    }
    data class NumberNotEq(val userEnteredNumber: String) : BooleanRuleCondition {
        override fun toBooleanRule() = booleanCondition {
            type = "NUMBER_NOT_EQ"
            setValues(userEnteredNumber.asValues())
        }
    }
    data class OneOfRange(val userEnteredRange: String) : BooleanRuleCondition {
        override fun toBooleanRule() = booleanCondition {
            type = "ONE_OF_RANGE"
            setValues(userEnteredRange.asValues())
        }
    }

}