package io.github.sikrinick.geshikt.api

import com.google.api.services.sheets.v4.model.CellFormat
import com.google.api.services.sheets.v4.model.NumberFormat
import com.google.api.services.sheets.v4.model.TextFormat

internal fun cellFormat(block: CellFormat.() -> Unit) =
    CellFormat().apply(block)

internal fun CellFormat.numberFormat(block: NumberFormat.() -> Unit) =
    NumberFormat().apply(block)
internal fun CellFormat.textFormat(block: TextFormat.() -> Unit) =
    TextFormat().apply(block)