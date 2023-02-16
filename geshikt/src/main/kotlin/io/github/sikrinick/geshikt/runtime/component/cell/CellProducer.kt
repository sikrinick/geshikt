package io.github.sikrinick.geshikt.runtime.component.cell

import com.google.api.services.sheets.v4.model.CellFormat as SheetCellFormat
import io.github.sikrinick.geshikt.dsl.component.Cell as UICell
import com.google.api.services.sheets.v4.model.CellData
import io.github.sikrinick.geshikt.api.cell
import io.github.sikrinick.geshikt.api.cellFormat

interface HasCellData {
    val cellData: CellData
}

internal interface CellFormatProducer {
    val change: SheetCellFormat.() -> Unit
}

internal class CellProcessor(
    private val cell: UICell,
    private val userEnteredValueProducer: UserEnteredValueProducer = UserEnteredValueProducer(cell),
    private val dataValidationProducer: DataValidationProducer = DataValidationProducer(cell),
    private val cellFormatProducers: List<CellFormatProducer> = listOf(
        BackgroundColorProducer(cell),
        NumberFormatProducer(cell),
        TextFormatProducer(cell),
        HAlignProducer(cell),
        VAlignProducer(cell),
        WrapProducer(cell)
    )
) : HasCellData {


    override val cellData = cell {
        dataValidation = dataValidationProducer.produce()
        userEnteredValue = userEnteredValueProducer.produce()
        userEnteredFormat = cellFormat {
            cellFormatProducers.map { it.change }.forEach(::apply)
        }
    }
}
