package io.github.sikrinick.geshikt.runtime.component.cell

import com.google.api.services.sheets.v4.model.CellFormat
import io.github.sikrinick.geshikt.dsl.component.Cell
import io.github.sikrinick.geshikt.dsl.component.style.HAlign
import io.github.sikrinick.geshikt.dsl.invoke

class HAlignProducer(
    private val uiCell: Cell
) : CellFormatProducer {

    private val uiHalign = uiCell.modifiers<HAlign>()

    override val change: CellFormat.() -> Unit = {
        uiHalign?.type?.map()?.let { horizontalAlignment = it }
    }

    private fun HAlign.Type.map() = when (this) {
        HAlign.Type.Unspecified -> null
        HAlign.Type.Left -> "LEFT"
        HAlign.Type.Center -> "CENTER"
        HAlign.Type.Right -> "RIGHT"
    }
}
