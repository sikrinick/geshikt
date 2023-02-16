package io.github.sikrinick.geshikt.dsl.component.style

import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier

class VAlign(val type: Type, root: Modifier? = null) : Modifier(root) {
    enum class Type { Unspecified, Top, Middle, Bottom }
}
fun Modifier.Companion.valign(alignment: VAlign.Type) = VAlign(alignment)
fun Modifier.valign(alignment: VAlign.Type) = VAlign(alignment, this)
