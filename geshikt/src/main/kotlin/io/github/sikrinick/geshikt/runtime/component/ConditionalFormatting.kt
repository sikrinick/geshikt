package io.github.sikrinick.geshikt.runtime.component

import io.github.sikrinick.geshikt.api.BooleanConditionProducer
import io.github.sikrinick.geshikt.api.booleanRule
import io.github.sikrinick.geshikt.api.cellFormat
import io.github.sikrinick.geshikt.api.conditionalFormatting
import io.github.sikrinick.geshikt.api.request
import io.github.sikrinick.geshikt.api.textFormat
import io.github.sikrinick.geshikt.dsl.component.HasModifiers
import io.github.sikrinick.geshikt.dsl.component.modifiers.ConditionalFormatting
import io.github.sikrinick.geshikt.dsl.invoke

interface ConditionalFormats : HasRequests

class ConditionalFormattingProcessor(
    hasModifiers: HasModifiers,
    grid: Grid,
    colorMapper: ColorMapper = ColorMapper(),
    booleanConditionProducer: BooleanConditionProducer = BooleanConditionProducer()
) : ConditionalFormats {
    private val conditionalFormats = hasModifiers.modifiers<ConditionalFormatting>()?.conditionalFormats

    override val requests = conditionalFormats?.let {
        conditionalFormats.map { (uiCondition, uiFormat) ->
            request {
                conditionalFormatting {
                    ranges = listOf(grid.range)
                    booleanRule = booleanRule {
                        condition = booleanConditionProducer.parse(uiCondition)
                        format = cellFormat {
                            textFormat = textFormat {
                                uiFormat.bold?.let { bold = it }
                                uiFormat.strikethrough?.let { strikethrough = it }
                                uiFormat.underline?.let { underline = it }
                                uiFormat.italic?.let { italic = it }
                                uiFormat.textColor?.let { foregroundColorStyle = colorMapper.map(it) }
                            }
                            uiFormat.backgroundColor?.let {
                                backgroundColorStyle = colorMapper.map(it)
                            }
                        }
                    }
                }
            }
        }
    } ?: emptyList()
}