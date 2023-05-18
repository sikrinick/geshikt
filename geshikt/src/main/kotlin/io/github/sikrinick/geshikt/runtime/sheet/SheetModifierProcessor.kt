package io.github.sikrinick.geshikt.runtime.sheet

import com.google.api.services.sheets.v4.model.Request

interface SheetModifierProcessor {
    fun requests(currentSheetId: Int): List<Request>
}
