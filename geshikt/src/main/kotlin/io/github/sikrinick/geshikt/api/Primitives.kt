package io.github.sikrinick.geshikt.api

import com.google.api.services.sheets.v4.model.BooleanCondition
import com.google.api.services.sheets.v4.model.CellData
import com.google.api.services.sheets.v4.model.Color
import com.google.api.services.sheets.v4.model.ColorStyle
import com.google.api.services.sheets.v4.model.DimensionRange
import com.google.api.services.sheets.v4.model.ExtendedValue
import com.google.api.services.sheets.v4.model.GridCoordinate
import com.google.api.services.sheets.v4.model.GridRange
import com.google.api.services.sheets.v4.model.Request
import com.google.api.services.sheets.v4.model.RowData


// Common
internal fun request(block: Request.() -> Unit) = Request().apply(block)

internal fun row(block: RowData.() -> Unit) = RowData().apply(block)
internal fun cell(block: CellData.() -> Unit) = CellData().apply(block)

// Grid
internal fun gridRange(block: GridRange.() -> Unit) = GridRange().apply(block)
internal fun coordinate(block: GridCoordinate.() -> Unit) = GridCoordinate().apply(block)

internal fun dimensionRange(block: DimensionRange.() -> Unit) = DimensionRange().apply(block)

internal fun color(block: Color.() -> Unit): ColorStyle = ColorStyle().setRgbColor(Color().apply(block))

internal fun extendedValue(block: ExtendedValue.() -> Unit) =
    ExtendedValue().apply(block)

internal fun booleanCondition(block: BooleanCondition.() -> Unit) =
    BooleanCondition().apply(block)