package io.github.sikrinick.geshikt.api

import com.google.api.services.sheets.v4.Sheets.Spreadsheets
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.BatchUpdate
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Create
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest
import com.google.api.services.sheets.v4.model.Spreadsheet
import com.google.api.services.sheets.v4.model.SpreadsheetProperties

fun spreadsheet(block: Spreadsheet.() -> Unit) =
    Spreadsheet().apply(block)

fun Spreadsheet.properties(block: SpreadsheetProperties.() -> Unit) =
    SpreadsheetProperties().apply(block)

fun Spreadsheets.create(title: String): Create =
    create(spreadsheet { properties = properties { this.title = title } })

fun Spreadsheets.batchUpdate(id: String, block: BatchUpdateSpreadsheetRequest.() -> Unit): BatchUpdate =
    batchUpdate(id, BatchUpdateSpreadsheetRequest().apply(block))