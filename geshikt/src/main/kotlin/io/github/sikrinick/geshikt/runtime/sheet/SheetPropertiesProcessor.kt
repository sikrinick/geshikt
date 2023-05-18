package io.github.sikrinick.geshikt.runtime.sheet

import com.google.api.services.sheets.v4.model.SheetProperties

interface SheetPropertiesProcessor {
    val field: String
    val change: SheetProperties.() -> Unit
}