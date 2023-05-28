package io.github.sikrinick.geshikt.dsl.values

import io.github.sikrinick.geshikt.dsl.component.Box
import io.github.sikrinick.geshikt.dsl.component.Cell
import io.github.sikrinick.geshikt.dsl.component.CellFormat
import io.github.sikrinick.geshikt.dsl.component.Column
import io.github.sikrinick.geshikt.dsl.component.HasCells
import io.github.sikrinick.geshikt.dsl.component.HasColumns
import io.github.sikrinick.geshikt.dsl.component.HasRows
import io.github.sikrinick.geshikt.dsl.component.Row
import io.github.sikrinick.geshikt.dsl.component.layout.Size
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.component.modifiers.named
import io.github.sikrinick.geshikt.dsl.types.FormulasEnv
import io.github.sikrinick.geshikt.dsl.types.HasFormulas
import io.github.sikrinick.geshikt.dsl.types.Type
import java.time.LocalDate

fun lazyCell(
    modifier: Modifier = Modifier.None,
    named: String,
    size: Size = Size(1, 1),
    cellFormat: CellFormat? = null
) = lazyCell(named, modifier, size, null, cellFormat)

fun lazyCell(
    modifier: Modifier = Modifier.None,
    named: String,
    size: Size = Size(1, 1),
    value: String, cellFormat: CellFormat? = null
) = lazyCell(named, modifier, size, Type.Hardcoded.Text(value), cellFormat)

fun lazyCell(
    modifier: Modifier = Modifier.None,
    named: String,
    size: Size = Size(1, 1),
    value: Number, cellFormat: CellFormat? = null
) = lazyCell(named, modifier, size, Type.Hardcoded.Number(value), cellFormat)

fun lazyCell(
    modifier: Modifier = Modifier.None,
    named: String,
    size: Size = Size(1, 1),
    value: Boolean,
    cellFormat: CellFormat? = null
) = lazyCell(named, modifier, size, Type.Hardcoded.Boolean(value), cellFormat)

fun lazyCell(
    modifier: Modifier = Modifier.None,
    named: String,
    size: Size = Size(1, 1),
    value: LocalDate,
    cellFormat: CellFormat? = null
) = lazyCell(named, modifier, size, Type.Hardcoded.Date(value), cellFormat)

fun lazyCell(
    modifier: Modifier = Modifier.None,
    named: String,
    size: Size = Size(1, 1),
    value: CellReference, cellFormat: CellFormat? = null
) = lazyCell(named, modifier, size, Type.CellReference.Any(value), cellFormat)

fun lazyCell(
    modifier: Modifier = Modifier.None,
    named: String,
    size: Size = Size(1, 1),
    cellFormat: CellFormat? = null,
    formula: HasFormulas.() -> Type.Formula
) = lazyCell(named, modifier, size, FormulasEnv().run(formula), cellFormat)

private fun lazyCell(
    name: String,
    modifier: Modifier,
    size: Size,
    value: Type?, cellFormat: CellFormat?
) = LazyCell(NamedCellReference(name), modifier, size, value, cellFormat)
fun lazyRow(named: String, modifier: Modifier = Modifier.None, block: Row.() -> Unit) =
    LazyRow(NamedCellRangeReference(named), modifier, block)
fun lazyColumn(named: String, modifier: Modifier = Modifier.None, block: Column.() -> Unit) =
    LazyColumn(NamedCellRangeReference(named), modifier, block)

class LazyCell internal constructor(
    val reference: NamedCellReference,
    internal val modifier: Modifier,
    internal val size: Size,
    internal val value: Type?,
    internal val cellFormat: CellFormat?
) {
    val name = reference.name
}

class LazyRow internal constructor(
    val reference: NamedCellRangeReference,
    internal val modifier: Modifier,
    internal val block: Row.() -> Unit
) {
    val name = reference.name
}

class LazyColumn internal constructor(
    val reference: NamedCellRangeReference,
    internal val modifier: Modifier,
    internal val block: Column.() -> Unit
) {
    val name = reference.name
}

class LazyBox internal constructor(
    internal val name: String,
    internal val modifier: Modifier,
    val reference: NamedCellRangeReference,
    internal val block: Box.() -> Unit
)

context(HasCells)
operator fun LazyCell.invoke(): Cell =
    cell(modifier.named(name), size, value, cellFormat)

context(HasRows)
operator fun LazyRow.invoke(nextBlock: Row.() -> Unit = {}): CellRangeReference = row(modifier.named(name)) {
    block()
    nextBlock()
}

context(HasColumns)
operator fun LazyColumn.invoke(nextBlock: Column.() -> Unit = {}): CellRangeReference = column(modifier.named(name)) {
    block()
    nextBlock()
}
