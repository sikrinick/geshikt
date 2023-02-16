package io.github.sikrinick.geshikt.dsl.component.modifiers

import io.github.sikrinick.geshikt.dsl.component.style.Color
import io.github.sikrinick.geshikt.dsl.types.Criterion
import io.github.sikrinick.geshikt.dsl.types.Expression
import io.github.sikrinick.geshikt.dsl.types.Type

// TODO. Refactor, make more readable
class ConditionalFormatting(
    val conditionalFormats: List<Pair<Type.Criterion, ConditionalFormat.ResultingFormat>>,
    root: Modifier?
) : Modifier(root)
fun Modifier.Companion.formatBy(vararg conditions: ConditionalFormat) =
    ConditionalFormatting(conditions.map { Expression().let(it.criterion) to it.format }, null)
fun Modifier.formatBy(vararg conditions: ConditionalFormat) =
    ConditionalFormatting(conditions.map { Expression().let(it.criterion) to it.format }, this)

data class ConditionalFormat(
    val criterion: Criterion,
    val format: ResultingFormat
) {
    data class ResultingFormat(
        val bold: Boolean? = null,
        val strikethrough: Boolean? = null,
        val underline: Boolean? = null,
        val italic: Boolean? = null,
        val textColor: Color? = null,
        val backgroundColor: Color? = null
    )
}