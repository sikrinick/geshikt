package io.github.sikrinick.geshikt.runtime.component.cell

import io.github.sikrinick.geshikt.dsl.component.Cell as UICell
import com.google.api.services.sheets.v4.model.CellFormat
import io.github.sikrinick.geshikt.dsl.component.style.Colored
import io.github.sikrinick.geshikt.dsl.invoke
import io.github.sikrinick.geshikt.runtime.component.ColorMapper

class BackgroundColorProducer(
    private val uiCell: UICell,
    private val mapper: ColorMapper = ColorMapper()
) : CellFormatProducer {
    private val colored = uiCell.modifiers<Colored>()

    override val change: CellFormat.() -> Unit = {
        colored?.let {
            backgroundColorStyle = mapper.map(it.color)
        }
    }
}