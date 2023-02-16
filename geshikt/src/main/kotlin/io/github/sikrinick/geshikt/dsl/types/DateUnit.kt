package io.github.sikrinick.geshikt.dsl.types

enum class DateUnit(val value: String) {
    Years("Y"),
    Months("M"),
    Days("D"),
    DaysAsNumber("YD"),
    MonthsAsNumber("YM"),
    //TODO: no MD?
}