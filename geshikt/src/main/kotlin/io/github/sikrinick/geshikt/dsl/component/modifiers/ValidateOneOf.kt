package io.github.sikrinick.geshikt.dsl.component.modifiers

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference

class ValidateOneOf(val reference: CellRangeReference, root: Modifier? = null) : Modifier(root)
fun Modifier.Companion.validateOneOf(reference: CellRangeReference) = ValidateOneOf(reference)
fun Modifier.validateOneOf(reference: CellRangeReference) = ValidateOneOf(reference, this)
