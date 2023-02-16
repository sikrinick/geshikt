package io.github.sikrinick.geshikt.runtime.component.cell

import com.google.api.services.sheets.v4.model.CellFormat
import io.github.sikrinick.geshikt.dsl.component.Cell
import io.github.sikrinick.geshikt.dsl.component.style.VAlign
import io.github.sikrinick.geshikt.dsl.invoke

class VAlignProducer(
    private val uiCell: Cell
) : CellFormatProducer {

    private val uiValign = uiCell.modifiers<VAlign>()

    override val change: CellFormat.() -> Unit = {
        uiValign?.type?.map()?.let { verticalAlignment = it }
    }

    private fun VAlign.Type.map() = when (this) {
        VAlign.Type.Unspecified -> null
        VAlign.Type.Top -> "TOP"
        VAlign.Type.Middle -> "MIDDLE"
        VAlign.Type.Bottom -> "BOTTOM"
    }
}
