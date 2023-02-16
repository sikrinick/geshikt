package io.github.sikrinick.geshikt.dsl.component

import io.github.sikrinick.geshikt.dsl.Modifiers
import io.github.sikrinick.geshikt.dsl.component.modifiers.BasicFilter
import io.github.sikrinick.geshikt.dsl.component.modifiers.Border
import io.github.sikrinick.geshikt.dsl.component.modifiers.ConditionalFormatting
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.component.modifiers.Named


typealias ComponentModifiers = Modifiers<Modifier>

interface HasModifiers {
    val modifiers: ComponentModifiers
    operator fun plus(other: HasModifiers): HasModifiers
}
class Modifiable(
    override val modifiers: ComponentModifiers
) : HasModifiers {
    override fun plus(other: HasModifiers) = Modifiable(modifiers + other.modifiers)

    fun propagate() = Modifiable(
        modifiers - listOf(
            Named::class,
            BasicFilter::class,
            Border::class,
            ConditionalFormatting::class
        )
    )
}



