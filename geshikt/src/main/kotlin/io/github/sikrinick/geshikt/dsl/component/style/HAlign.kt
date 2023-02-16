package io.github.sikrinick.geshikt.dsl.component.style

import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier

class HAlign(val type: Type, root: Modifier? = null) : Modifier(root) {
    enum class Type { Unspecified, Left, Center, Right }
}
fun Modifier.Companion.halign(alignment: HAlign.Type) = HAlign(alignment)
fun Modifier.halign(alignment: HAlign.Type) = HAlign(alignment, this)
