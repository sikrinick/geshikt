package io.github.sikrinick.geshikt.runtime.component

import io.github.sikrinick.geshikt.dsl.component.RepeatCell as UIRepeatCell
import io.github.sikrinick.geshikt.api.repeatCell
import io.github.sikrinick.geshikt.api.request
import io.github.sikrinick.geshikt.runtime.component.cell.CellProcessor
import io.github.sikrinick.geshikt.runtime.component.cell.HasCellData

class RepeatCell(
    component: Component,
    repeatCell: UIRepeatCell,
    cellData: HasCellData = CellProcessor(repeatCell.cell)
) : Grid by component,
    HasCellData by cellData,
    HasRequests by component {

    override val requests = component.requests +
        request {
            repeatCell {
                cell = this@RepeatCell.cellData
                range = this@RepeatCell.range
            }
        }
}