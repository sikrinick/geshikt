package io.github.sikrinick.geshikt.dsl

import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
class Spreadsheet internal constructor(
    internal val id: String? = null,
    internal val title: String? = null
) {

    val sheets = mutableListOf<Sheet>()

    init {
        id ?: title ?: throw IllegalArgumentException("Spreadsheet should have either a name, or an ID")
    }

    fun sheet(
        name: String,
        modifier: Sheet.Modifier = Sheet.Modifier.None,
        boxModifier: Modifier = Modifier.None,
        block: Sheet.() -> Unit
    ) {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        Sheet(SheetTitle(name), modifier, boxModifier).apply(block).apply(sheets::add)
    }

    @JvmInline value class Title(val value: String)
    @JvmInline value class Id(val value: String)
}

fun id(id: String) = Spreadsheet.Id(id)
fun title(title: String) = Spreadsheet.Title(title)

fun spreadsheet(id: Spreadsheet.Id, block: Spreadsheet.() -> Unit) = Spreadsheet(id.value, null).apply(block)
fun spreadsheet(title: Spreadsheet.Title, block: Spreadsheet.() -> Unit) = Spreadsheet(null, title.value).apply(block)