package io.github.sikrinick.geshikt.dsl.utils

import io.github.sikrinick.geshikt.dsl.component.Column
import io.github.sikrinick.geshikt.dsl.component.HasColumns
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.invoke
import io.github.sikrinick.geshikt.dsl.values.lazyColumn

fun HasColumns.valueColumn(
    header: String,
    headerModifier: Modifier = Modifier.None,
    block: Column.() -> Unit
) = valueColumn(header, headerModifier, header.asSheetRefName(), block)
fun HasColumns.valueColumn(
    header: String,
    headerModifier: Modifier = Modifier.None,
    named: String,
    block: Column.() -> Unit
): CellRangeReference {
    val values = lazyColumn(named) {
        block()
    }
    column {
        cell(header, headerModifier)
        values()
    }
    return values.reference
}