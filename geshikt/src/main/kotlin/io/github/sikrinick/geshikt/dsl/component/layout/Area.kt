package io.github.sikrinick.geshikt.dsl.component.layout

interface Area : HasSize, HasPosition {
    val endX get() = startX + width
    val endY get() = startY + height
}

data class Rectangle(
    val position: HasPosition,
    val size: Size
) : Area,
    HasPosition by position,
    HasSize by size
{
    fun toMutable() = ResizableRectangle(
        position,
        size = MutableSize(height = size.height, width = size.width)
    )
}

data class ResizableRectangle(
    val position: HasPosition,
    val size: MutableSize,
) : Area,
    HasPosition by position,
    HasSize by size