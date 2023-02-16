package io.github.sikrinick.geshikt.dsl.component

import io.github.sikrinick.geshikt.dsl.component.layout.Area
import io.github.sikrinick.geshikt.dsl.component.layout.ResizableRectangle
import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference
import io.github.sikrinick.geshikt.dsl.values.RangeReferencer

class Box internal constructor(
    private val modifiable: Modifiable,
    private val childrenList: ChildrenList,
    private val rectangle: ResizableRectangle,
) : Container,
    Area by rectangle,
    HasChildren by childrenList,
    HasRows by Column(modifiable.propagate(), childrenList, rectangle),
    HasColumns by Row(modifiable.propagate(), childrenList, rectangle),
    HasCellRangeReference by RangeReferencer(modifiable, rectangle),
    HasModifiers by modifiable {
}