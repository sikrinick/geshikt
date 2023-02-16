package io.github.sikrinick.geshikt.dsl.types

class Today(

) : Type.Formula.ReturnsDate, Type.Formula by UseFormula(
    "TODAY"
)

interface HasToday {

    fun today() = Today()

}