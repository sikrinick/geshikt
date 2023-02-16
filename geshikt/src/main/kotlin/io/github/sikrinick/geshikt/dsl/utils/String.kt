package io.github.sikrinick.geshikt.dsl.utils

fun String.asSheetRefName(parent: String? = null): String = (parent?.asSheetRefName() ?: "") + this
    .replace(Regex("[().,]"), " ")
    .split(" ")
    .joinToString("") {
        it.replaceFirstChar { char -> char.titlecaseChar() }
    }
