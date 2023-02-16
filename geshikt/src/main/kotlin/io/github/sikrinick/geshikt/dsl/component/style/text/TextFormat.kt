package io.github.sikrinick.geshikt.dsl.component.style.text

import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier

class TextBold(val bold: Boolean, root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.bold(bold: Boolean = true) = TextBold(bold)
fun Modifier.bold(bold: Boolean = true) = TextBold(bold, this)

class TextItalic(val italic: Boolean, root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.italic(italic: Boolean = true) = TextItalic(italic)
fun Modifier.italic(italic: Boolean = true) = TextItalic(italic, this)

class TextStrikethrough(val strikeThrough: Boolean, root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.strikethrough(strikethrough: Boolean = true) = TextStrikethrough(strikethrough)
fun Modifier.strikethrough(strikethrough: Boolean = true) = TextStrikethrough(strikethrough, this)

class TextUnderline(val underline: Boolean, root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.underline(underline: Boolean = true) = TextUnderline(underline)
fun Modifier.underline(underline: Boolean = true) = TextUnderline(underline, this)
