package io.github.sikrinick.geshikt.dsl.component.style.text

import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier

class FontModifier(val font: Font, root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.font(font: Font) = FontModifier(font, null)
fun Modifier.font(font: Font) = FontModifier(font, this)

class FontSize(val size: Int, root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.fontSize(size: Int) = FontSize(size, null)
fun Modifier.fontSize(size: Int) = FontSize(size, this)

sealed class Font(val fontFamily: String) {
    object Arial : Font("Arial")
    object TimesNewRoman : Font("Times New Roman")
    object Inconsolata : Font("Inconsolata")
    object CourierNew : Font("CourierNew")
    object ComicSansMS : Font("Comic Sans MS")

    class Other(font: String) : Font(font)
}