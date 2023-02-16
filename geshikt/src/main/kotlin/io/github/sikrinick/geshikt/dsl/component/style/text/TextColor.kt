package io.github.sikrinick.geshikt.dsl.component.style.text

import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.component.style.Color

class TextColor(val color: Color, root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.textColor(color: Color) = TextColor(color, null)
fun Modifier.textColor(color: Color) = TextColor(color, this)
