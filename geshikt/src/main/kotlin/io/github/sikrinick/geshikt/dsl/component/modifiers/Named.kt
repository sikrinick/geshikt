package io.github.sikrinick.geshikt.dsl.component.modifiers

class Named(val name: String, root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.named(name: String) = Named(name, null)
fun Modifier.named(name: String) = Named(name, this)
