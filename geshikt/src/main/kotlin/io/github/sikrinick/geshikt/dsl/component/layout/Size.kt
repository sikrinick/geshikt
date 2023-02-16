package io.github.sikrinick.geshikt.dsl.component.layout

interface HasSize {
    val height: Int
    val width: Int
}

data class Size(
    override val height: Int,
    override val width: Int
) : HasSize {
    fun toMutable() = MutableSize(
        height = height,
        width = width
    )
}

data class MutableSize(
    override var height: Int,
    override var width: Int
) : HasSize