package io.github.sikrinick.geshikt.runtime.component.cell

import io.github.sikrinick.geshikt.api.cell
import io.github.sikrinick.geshikt.api.request
import io.github.sikrinick.geshikt.api.row
import io.github.sikrinick.geshikt.api.updateCells
import io.github.sikrinick.geshikt.dsl.component.Cell
import io.github.sikrinick.geshikt.runtime.component.Component
import io.github.sikrinick.geshikt.runtime.component.Extends
import io.github.sikrinick.geshikt.runtime.component.Grid
import io.github.sikrinick.geshikt.runtime.component.HasRequests
import io.github.sikrinick.geshikt.runtime.component.SizeProcessor

class Cell(
    component: Component,
    uiCell: Cell,
    extends: Extends = SizeProcessor(uiCell, component),
    cellData: HasCellData = CellProcessor(uiCell)
) : Grid by component,
    Extends by extends,
    HasCellData by cellData,
    HasRequests
{
    override val requests = component.requests + extends.requests +
        request {
            updateCells {
                row {
                    cell(this@Cell.cellData)
                }
                start = this@Cell.coordinate
            }
        }
}