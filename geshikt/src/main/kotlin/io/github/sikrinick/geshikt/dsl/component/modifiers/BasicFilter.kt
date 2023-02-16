package io.github.sikrinick.geshikt.dsl.component.modifiers

class BasicFilter(root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.basicFilter() = BasicFilter(null)
fun Modifier.basicFilter() = BasicFilter(this)
