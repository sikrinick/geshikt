package io.github.sikrinick.geshikt.dsl.component.style

import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier

class Wrap(val type: Type, root: Modifier? = null) : Modifier(root) {
    enum class Type { Unspecified, Overflow, Clip, Wrap }
}
fun Modifier.Companion.wrap(wrap: Wrap.Type) = Wrap(wrap)
fun Modifier.wrap(wrap: Wrap.Type) = Wrap(wrap, this)
