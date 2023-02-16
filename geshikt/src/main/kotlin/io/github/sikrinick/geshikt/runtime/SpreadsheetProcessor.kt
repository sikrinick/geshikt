package io.github.sikrinick.geshikt.runtime

import io.github.sikrinick.geshikt.dsl.Spreadsheet as UISpreadsheet
import com.google.api.services.sheets.v4.Sheets.Spreadsheets
import com.google.api.services.sheets.v4.model.Request
import io.github.sikrinick.geshikt.ProcessingContext
import io.github.sikrinick.geshikt.api.batchUpdate
import io.github.sikrinick.geshikt.api.create
import io.github.sikrinick.geshikt.runtime.sheet.SheetProcessor

internal class SpreadsheetProcessor(
    private val uiSpreadsheet: UISpreadsheet,
    private val context: ProcessingContext,
    private val knownSpreadsheets: Lazy<List<GoogleSpreadsheet>>,
    private val api: Spreadsheets
) {

    fun process() {
        val googleSpreadsheet = with(uiSpreadsheet) {
            val knownSpreadsheetsMap = knownSpreadsheets.value.associateBy { it.title }
            when {
                id != null -> api.get(id)
                title == null -> throw IllegalArgumentException("Spreadsheet should have either and id or a name")
                title in knownSpreadsheetsMap.keys -> api.get(knownSpreadsheetsMap.getValue(title).id)
                else -> api.create(title)
            }
        }.execute()

        val spreadsheetId = googleSpreadsheet.spreadsheetId

        uiSpreadsheet.sheets
            .map { SheetProcessor(it, context, googleSpreadsheet.sheets) }
            .map { it to it.createSheet.execute(spreadsheetId) }
            .map { (proc, resp) -> proc to proc.getId(resp) }
            .flatMap { (proc, id) -> proc.updateSheet(id) }
            .forEach { it.execute(spreadsheetId) }
    }

    private fun List<Request>.execute(spreadsheetId: String) = takeIf { isNotEmpty() }?.let {
        api.batchUpdate(spreadsheetId) { requests = it }.execute()
    }
}