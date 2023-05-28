package io.github.sikrinick.geshikt.dsl.utils

import io.github.sikrinick.geshikt.dsl.component.Column
import io.github.sikrinick.geshikt.dsl.component.HasColumns
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.component.modifiers.named
import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.PositionedCellRangeReference
import io.github.sikrinick.geshikt.dsl.values.invoke
import io.github.sikrinick.geshikt.dsl.values.lazyColumn

fun HasColumns.valueColumn(
    header: String,
    named: String,
    headerModifier: Modifier = Modifier.None,
    block: Column.() -> Unit
) = columnWithHeader(
    header = header,
    headerModifier = headerModifier,
    columnModifier = Modifier.named(named),
    block
)

fun HasColumns.columnWithHeader(
    header: String,
    headerModifier: Modifier = Modifier.None,
    columnModifier: Modifier = Modifier.None,
    block: Column.() -> Unit
): PositionedCellRangeReference {
    lateinit var reference: PositionedCellRangeReference
    column {
        cell(header, headerModifier)
        reference = column(columnModifier) {
            block()
        }
    }
    return reference
}