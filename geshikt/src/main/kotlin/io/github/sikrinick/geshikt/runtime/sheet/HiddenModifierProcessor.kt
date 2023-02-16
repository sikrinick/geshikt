package io.github.sikrinick.geshikt.runtime.sheet

import com.google.api.services.sheets.v4.model.SheetProperties
import io.github.sikrinick.geshikt.dsl.Sheet
import io.github.sikrinick.geshikt.dsl.invoke

class HiddenModifierProcessor(
    val sheet: Sheet
) : SheetModifierProcessor {

    private val hiddenModifier = sheet.sheetModifiers<Sheet.Modifier.Hidden>()

    override val field = hiddenModifier?.let { "hidden" } ?: ""
    override val change: SheetProperties.() -> Unit = {
        hiddenModifier?.let { hidden = it.hidden }
    }
}