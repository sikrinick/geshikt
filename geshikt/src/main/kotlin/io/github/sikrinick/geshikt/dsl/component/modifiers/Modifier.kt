package io.github.sikrinick.geshikt.dsl.component.modifiers

import io.github.sikrinick.geshikt.dsl.AnyModifier
import io.github.sikrinick.geshikt.dsl.component.Modifiable

abstract class Modifier(override var root: Modifier? = null) : AnyModifier<Modifier> {

    object None : Modifier(null)

    fun toModifiable() = Modifiable(unfold())

    fun apply(modifier: Modifier) {
        if (root != null) throw IllegalArgumentException("Cannot apply to a modifier, which already has root")
        root = modifier
    }

    companion object
}