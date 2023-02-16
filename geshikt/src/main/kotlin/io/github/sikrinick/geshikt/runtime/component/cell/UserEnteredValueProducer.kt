package io.github.sikrinick.geshikt.runtime.component.cell

import io.github.sikrinick.geshikt.dsl.component.Cell as UICell
import io.github.sikrinick.geshikt.api.extendedValue
import io.github.sikrinick.geshikt.dsl.types.Type

class UserEnteredValueProducer(
    private val uiCell: UICell,
    private val typeProcessor: TypeProcessor = TypeProcessor()
) {
    fun produce() = uiCell.value?.let {
        extendedValue {
            with (it) {
                when (this) {
                    is Type.Hardcoded.Text -> stringValue = text
                    is Type.Hardcoded.Number -> numberValue = number.toDouble()
                    is Type.Hardcoded.Boolean -> boolValue = boolean
                    is Type.Hardcoded.Date -> stringValue = typeProcessor.process(this)
                    else -> formulaValue = "=${typeProcessor.process(this)}"
                }
            }
        }
    }
}