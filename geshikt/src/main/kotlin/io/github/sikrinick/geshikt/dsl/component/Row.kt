package io.github.sikrinick.geshikt.dsl.component

import io.github.sikrinick.geshikt.dsl.component.layout.ResizableRectangle
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.PositionedCellRangeReference

interface HasRows {
    fun row(modifier: Modifier = Modifier.None, block: Row.() -> Unit): PositionedCellRangeReference
}

class Row internal constructor(
    modifiable: Modifiable,
    childrenList: ChildrenList,
    rectangle: ResizableRectangle
) : Line by SingleLine(
    modifiable,
    childrenList,
    rectangle,
    Vector.Down,
    Axis.Horizontal
)


