package io.github.sikrinick.geshikt.dsl.component

sealed interface CellFormat {
    object Automatic : CellFormat

    object Text : CellFormat
    object Number : CellFormat
    @JvmInline value class Date(val pattern: String = "dd.mm.yyyy") : CellFormat
}