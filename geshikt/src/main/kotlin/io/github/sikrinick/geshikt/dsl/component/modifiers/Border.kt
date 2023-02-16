package io.github.sikrinick.geshikt.dsl.component.modifiers

class Border(
    val top: Boolean?,
    val left: Boolean?,
    val right: Boolean?,
    val bottom: Boolean?,
    val innerVertical: Boolean?,
    val innerHorizontal: Boolean?,
    root: Modifier? = null
) : Modifier(root)
fun Modifier.Companion.border(
    top: Boolean? = null,
    left: Boolean? = null,
    right: Boolean? = null,
    bottom: Boolean? = null,
    innerVertical: Boolean? = null,
    innerHorizontal: Boolean? = null,
) = Border(top, left, right, bottom, innerVertical, innerHorizontal, null)
fun Modifier.Companion.allBorders() = Modifier.border(
    top = true,
    left = true,
    right = true,
    bottom = true,
    innerVertical = true,
    innerHorizontal = true
)
fun Modifier.Companion.outerBorders() = Modifier.border(
    top = true,
    left = true,
    right = true,
    bottom = true,
    innerVertical = false,
    innerHorizontal = false
)
fun Modifier.border(
    top: Boolean? = null,
    left: Boolean? = null,
    right: Boolean? = null,
    bottom: Boolean? = null,
    innerVertical: Boolean? = null,
    innerHorizontal: Boolean? = null,
) = Border(top, left, right, bottom, innerVertical, innerHorizontal, this)
fun Modifier.allBorders() = border(
    top = true,
    left = true,
    right = true,
    bottom = true,
    innerVertical = true,
    innerHorizontal = true
)
fun Modifier.outerBorders() = border(
    top = true,
    left = true,
    right = true,
    bottom = true,
    innerVertical = false,
    innerHorizontal = false
)
