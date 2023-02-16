package io.github.sikrinick.geshikt.dsl.component

import io.github.sikrinick.geshikt.dsl.component.layout.Area
import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference
import io.github.sikrinick.geshikt.dsl.values.RangeReferencer

data class RepeatCell internal constructor(
    val times: Int,
    private val axis: Axis,
    private val modifiable: Modifiable,
    val cell: Cell,
    private val area: Area = LengthyArea(times, axis, base = cell),
) : Component,
    Area by area,
    HasCellRangeReference by RangeReferencer(modifiable, area),
    HasModifiers by modifiable

