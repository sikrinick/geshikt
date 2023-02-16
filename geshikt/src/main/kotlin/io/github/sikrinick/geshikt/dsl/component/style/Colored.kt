package io.github.sikrinick.geshikt.dsl.component.style

import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier

class Colored(val color: Color, root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.colored(color: Color) = Colored(color)
fun Modifier.colored(color: Color) = Colored(color, this)
