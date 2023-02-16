package io.github.sikrinick.geshikt.runtime.component.cell

import io.github.sikrinick.geshikt.dsl.component.Cell as UICell
import io.github.sikrinick.geshikt.dsl.component.CellFormat as UICellFormat
import com.google.api.services.sheets.v4.model.CellFormat
import io.github.sikrinick.geshikt.api.numberFormat
import io.github.sikrinick.geshikt.dsl.types.Type

class NumberFormatProducer(
    private val uiCell: UICell
) : CellFormatProducer {

    private val cellFormat = uiCell.overrideFormat ?: when(uiCell.value) {
        is Type.Hardcoded.Text -> UICellFormat.Text
        is Type.Hardcoded.Number -> UICellFormat.Number
        is Type.Hardcoded.Date -> UICellFormat.Date()
        else -> UICellFormat.Automatic
    }

    override val change: CellFormat.() -> Unit = {
        numberFormat = numberFormat {
            when (cellFormat) {
                UICellFormat.Text -> type = "TEXT"
                UICellFormat.Number -> type = "NUMBER"
                is UICellFormat.Date -> {
                    type = "DATE"
                    pattern = cellFormat.pattern
                }
                UICellFormat.Automatic -> {
                    //type = "NUMBER_FORMAT_TYPE_UNSPECIFIED"
                }
            }
        }
    }
}