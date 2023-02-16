package io.github.sikrinick.geshikt

data class ProcessingContext(
    val maxRowCount: Int
)

val DefaultContext = ProcessingContext(
    maxRowCount = 999
)