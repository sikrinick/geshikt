package io.github.sikrinick.geshikt.dsl.component

import io.github.sikrinick.geshikt.dsl.component.layout.Area
import io.github.sikrinick.geshikt.dsl.component.layout.HasSize
import io.github.sikrinick.geshikt.dsl.component.layout.Position
import io.github.sikrinick.geshikt.dsl.component.layout.Size

enum class Axis {
    Horizontal, Vertical
}

sealed interface Vector {

    fun nextPosition(area: Area): Position
    fun resize(current: Area, basedOn: HasSize): Size

    object Right : Vector {
        override fun nextPosition(area: Area) = Position(area.title, area.startX, area.endY)
        override fun resize(current: Area, basedOn: HasSize) = Size(
            width = maxOf(current.width, basedOn.width),
            height = current.height + basedOn.height
        )
    }

    object Down : Vector {
        override fun nextPosition(area: Area) = Position(area.title, area.endX, area.startY)
        override fun resize(current: Area, basedOn: HasSize) = Size(
            height = maxOf(current.height, basedOn.height),
            width = current.width + basedOn.width
        )
    }

    object Both : Vector {
        override fun nextPosition(area: Area) = Position(area.title, area.endX, area.endY)
        override fun resize(current: Area, basedOn: HasSize) = Size(
            height = current.height + basedOn.height,
            width = current.width + basedOn.width
        )
    }
}