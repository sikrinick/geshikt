package io.github.sikrinick.geshikt.runtime.component.cell

import io.github.sikrinick.geshikt.dsl.component.Cell as UICell
import com.google.api.services.sheets.v4.model.CellFormat
import io.github.sikrinick.geshikt.api.textFormat
import io.github.sikrinick.geshikt.dsl.component.style.text.FontModifier
import io.github.sikrinick.geshikt.dsl.component.style.text.FontSize
import io.github.sikrinick.geshikt.dsl.component.style.text.TextBold
import io.github.sikrinick.geshikt.dsl.component.style.text.TextColor
import io.github.sikrinick.geshikt.dsl.component.style.text.TextItalic
import io.github.sikrinick.geshikt.dsl.component.style.text.TextStrikethrough
import io.github.sikrinick.geshikt.dsl.component.style.text.TextUnderline
import io.github.sikrinick.geshikt.dsl.invoke
import io.github.sikrinick.geshikt.runtime.component.ColorMapper

class TextFormatProducer(
    private val uiCell: UICell,
    private val mapper: ColorMapper = ColorMapper()
) : CellFormatProducer {

    private val fontModifier = uiCell.modifiers<FontModifier>()
    private val uiFontSize = uiCell.modifiers<FontSize>()
    private val textColor = uiCell.modifiers<TextColor>()
    private val textBold = uiCell.modifiers<TextBold>()
    private val textItalic = uiCell.modifiers<TextItalic>()
    private val textStrikethrough = uiCell.modifiers<TextStrikethrough>()
    private val textUnderline = uiCell.modifiers<TextUnderline>()

    override val change: CellFormat.() -> Unit = {
        textFormat = textFormat {
            textBold?.let { bold = it.bold }
            textStrikethrough?.let { strikethrough = it.strikeThrough }
            textUnderline?.let { underline = it.underline }
            textItalic?.let { italic = it.italic }

            fontModifier?.let { fontFamily = it.font.fontFamily }
            uiFontSize?.let { fontSize = it.size }

            textColor?.let { foregroundColorStyle = mapper.map(it.color) }
        }
    }
}