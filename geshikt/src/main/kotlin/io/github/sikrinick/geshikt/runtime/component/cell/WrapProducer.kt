package io.github.sikrinick.geshikt.runtime.component.cell

import com.google.api.services.sheets.v4.model.CellFormat
import io.github.sikrinick.geshikt.dsl.component.Cell
import io.github.sikrinick.geshikt.dsl.component.style.Wrap
import io.github.sikrinick.geshikt.dsl.invoke

class WrapProducer(
    private val uiCell: Cell
) : CellFormatProducer {

    private val wrap = uiCell.modifiers<Wrap>()

    override val change: CellFormat.() -> Unit = {
        wrap?.type?.map()?.let { wrapStrategy = it }
    }

    private fun Wrap.Type.map() = when (this) {
        Wrap.Type.Unspecified -> null
        Wrap.Type.Overflow -> "OVERFLOW"
        Wrap.Type.Clip -> "CLIP"
        Wrap.Type.Wrap -> "WRAP"
    }
}
