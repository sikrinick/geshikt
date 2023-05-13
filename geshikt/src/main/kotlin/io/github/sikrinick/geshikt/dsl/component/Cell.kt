package io.github.sikrinick.geshikt.dsl.component

import io.github.sikrinick.geshikt.dsl.component.layout.Area
import io.github.sikrinick.geshikt.dsl.component.layout.Size
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.types.FormulasEnv
import io.github.sikrinick.geshikt.dsl.types.HasFormulas
import io.github.sikrinick.geshikt.dsl.types.Type
import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference
import io.github.sikrinick.geshikt.dsl.values.CellReferencer
import io.github.sikrinick.geshikt.dsl.values.HasCellReference
import java.time.LocalDate

interface HasCells {

    fun cell(modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        cell(modifier, size, null, cellFormat)

    fun cell(value: String, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        cell(modifier, size, Type.Hardcoded.Text(value), cellFormat)

    fun cell(value: Number, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        cell(modifier, size, Type.Hardcoded.Number(value), cellFormat)

    fun cell(value: Boolean, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        cell(modifier, size, Type.Hardcoded.Boolean(value), cellFormat)

    fun cell(value: LocalDate, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        cell(modifier, size, Type.Hardcoded.Date(value), cellFormat)

    fun cell(value: CellReference, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        cell(modifier, size, Type.CellReference.Any(value), cellFormat)

    fun cell(
        modifier: Modifier = Modifier.None,
        size: Size = Size(1, 1),
        cellFormat: CellFormat? = null,
        formula: HasFormulas.() -> Type.Formula
    ) = cell(modifier, size, FormulasEnv().run(formula), cellFormat)

    fun cell(
        modifier: Modifier,
        size: Size,
        value: Type?,
        cellFormat: CellFormat?
    ) : Cell

    fun repeatCell(times: Int, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        repeatCell(times, modifier, size, null, cellFormat)

    fun repeatCell(times: Int, value: String, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        repeatCell(times, modifier, size, Type.Hardcoded.Text(value), cellFormat)

    fun repeatCell(times: Int, value: Number, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        repeatCell(times, modifier, size, Type.Hardcoded.Number(value), cellFormat)

    fun repeatCell(times: Int, value: Boolean, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        repeatCell(times, modifier, size, Type.Hardcoded.Boolean(value), cellFormat)

    fun repeatCell(times: Int, value: LocalDate, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        repeatCell(times, modifier, size, Type.Hardcoded.Date(value), cellFormat)

    fun repeatCell(times: Int, value: CellReference, modifier: Modifier = Modifier.None, size: Size = Size(1, 1), cellFormat: CellFormat? = null) =
        repeatCell(times, modifier, size, Type.CellReference.Any(value), cellFormat)

    fun repeatCell(
        times: Int,
        modifier: Modifier = Modifier.None,
        size: Size = Size(1, 1),
        cellFormat: CellFormat? = null,
        formula: HasFormulas.() -> Type.Formula,
    ) = repeatCell(times, modifier, size, FormulasEnv().run(formula), cellFormat)

    fun repeatCell(
        times: Int,
        modifier: Modifier,
        size: Size,
        value: Type?,
        cellFormat: CellFormat?
    ) : CellRangeReference

    fun space(size: Int)

}

data class Cell(
    val modifiable: Modifiable,
    val area: Area,
    val value: Type?,
    val overrideFormat: CellFormat?
) : Component, Area by area,
    HasCellReference by CellReferencer(modifiable, area),
    HasModifiers by modifiable
