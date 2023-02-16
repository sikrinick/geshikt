package io.github.sikrinick.geshikt.dsl.component

import io.github.sikrinick.geshikt.dsl.component.layout.Area
import io.github.sikrinick.geshikt.dsl.component.layout.HasPosition
import io.github.sikrinick.geshikt.dsl.component.layout.HasSize

class LengthyArea(
    times: Int,
    axis: Axis,
    base: Area
) : Area, HasPosition by base, HasSize {
    override val height = base.height * when (axis) {
        Axis.Vertical -> times
        Axis.Horizontal -> 1
    }

    override val width = base.width * when (axis) {
        Axis.Horizontal -> times
        Axis.Vertical -> 1
    }
}