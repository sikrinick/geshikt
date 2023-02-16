package io.github.sikrinick.geshikt.runtime.component

import io.github.sikrinick.geshikt.dsl.component.Cell as UICell
import io.github.sikrinick.geshikt.dsl.component.Container as UIContainer
import io.github.sikrinick.geshikt.dsl.component.RepeatCell as UIRepeatCell
import io.github.sikrinick.geshikt.runtime.component.cell.Cell

interface HasChildren : HasRequests
class ChildrenProcessor(
    sheetId: Int,
    uiContainer: UIContainer
) : HasChildren {
    override val requests = uiContainer.children.flatMap {
        val component = Component(sheetId, it)
        when(it) {
            is UICell -> Cell(component, it)
            is UIRepeatCell -> RepeatCell(component, it)
            is UIContainer -> Container(component, it)
        }.requests
    }
}