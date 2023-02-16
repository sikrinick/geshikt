package io.github.sikrinick.geshikt.api

import com.google.api.services.sheets.v4.model.CellData
import com.google.api.services.sheets.v4.model.RowData

internal fun RowData.cell(block: CellData.() -> Unit) = setValues(
    (getValues() ?: mutableListOf()) + CellData().apply(block)
)

internal fun RowData.cell(cellData: CellData) = setValues(
    (getValues() ?: mutableListOf()) + cellData
)