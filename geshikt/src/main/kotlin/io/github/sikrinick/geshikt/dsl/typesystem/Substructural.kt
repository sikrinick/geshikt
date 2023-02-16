package io.github.sikrinick.geshikt.dsl.typesystem

// TODO! Use for lazy references, in order to guarantee invocation
sealed interface Substructural

class Linear {

    val exception = VariableException("Variable should be used exactly once")
    var isUsed = false

    fun use() = if (isUsed) throw exception else isUsed = true

    fun check() {
        if (isUsed.not()) throw exception
    }

}

class VariableException(message: String) : Exception(message)
