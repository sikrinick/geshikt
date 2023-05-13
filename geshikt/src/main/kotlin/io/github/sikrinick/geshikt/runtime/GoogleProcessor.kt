package io.github.sikrinick.geshikt.runtime

import io.github.sikrinick.geshikt.dsl.spreadsheet as uiSpreadsheet
import io.github.sikrinick.geshikt.DefaultContext
import io.github.sikrinick.geshikt.ProcessingContext
import io.github.sikrinick.geshikt.api.GoogleClient
import io.github.sikrinick.geshikt.dsl.Spreadsheet

class GoogleProcessor internal constructor(
    val context: ProcessingContext,
    private val googleClient: GoogleClient = GoogleClient(),
    private val spreadsheets: MutableList<Spreadsheet> = mutableListOf(),
) {
    private val sheetsApi = googleClient.sheets.spreadsheets()
    private val driveApi = googleClient.drive

    fun spreadsheet(id: Spreadsheet.Id, block: Spreadsheet.() -> Unit) =
        uiSpreadsheet(id, block).let(spreadsheets::add)
    fun spreadsheet(title: Spreadsheet.Title, block: Spreadsheet.() -> Unit) =
        uiSpreadsheet(title, block).let(spreadsheets::add)

    internal fun render() {
        spreadsheets
            .map { SpreadsheetProcessor(it, context, existingSpreadsheets, sheetsApi) }
            .forEach { it.process() }
    }

    private val existingSpreadsheets = lazy {
        driveApi.Files().list()
            .apply {
                q = "mimeType='application/vnd.google-apps.spreadsheet'"
                fields = "nextPageToken, files(id, name)"
            }
            .execute()
            .files
            .map {
                GoogleSpreadsheet(
                    id = it.id,
                    title = it.name
                )
            }
    }
}

fun process(
    context: ProcessingContext = DefaultContext,
    block: GoogleProcessor.() -> Unit
) = GoogleProcessor(context).apply(block).render()

fun process(
    spreadsheet: Spreadsheet,
    context: ProcessingContext = DefaultContext
) = GoogleProcessor(context, spreadsheets = mutableListOf(spreadsheet)).render()

fun process(
    spreadsheets: List<Spreadsheet>,
    context: ProcessingContext = DefaultContext
) = GoogleProcessor(context, spreadsheets = spreadsheets.toMutableList()).render()
