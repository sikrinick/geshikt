package io.github.sikrinick.geshikt.runtime.component.cell

import com.google.api.services.sheets.v4.model.DataValidationRule
import io.github.sikrinick.geshikt.api.BooleanConditionProducer
import io.github.sikrinick.geshikt.dsl.component.Cell
import io.github.sikrinick.geshikt.dsl.component.modifiers.ValidateOneOf
import io.github.sikrinick.geshikt.dsl.invoke

class DataValidationProducer(
    val cell: Cell,
    private val booleanConditionProducer: BooleanConditionProducer = BooleanConditionProducer()
) {

    private val oneOfModifier = cell.modifiers<ValidateOneOf>()

    fun produce() = oneOfModifier?.let {
        DataValidationRule().apply {
            strict = true
            showCustomUi = true
            condition = booleanConditionProducer.oneOfRange(it.reference)
        }
    }

}