package io.github.sikrinick.geshikt.runtime.component

import io.github.sikrinick.geshikt.api.mergeCells
import io.github.sikrinick.geshikt.api.request
import io.github.sikrinick.geshikt.dsl.component.layout.HasSize

interface Extends : HasRequests

class SizeProcessor(
    size: HasSize,
    grid: Grid,
) : Extends {
    enum class MergeType(val value: String) {
        RowsOnly("MERGE_ROWS"),
        ColumnsOnly("MERGE_COLUMNS"),
        All("MERGE_ALL")
    }

    override val requests = size.takeIf { it.height > 1 || it.width > 1 }?.let {
        request {
            mergeCells {
                mergeType = MergeType.All.value
                range = grid.range
            }
        }
    }.let(::listOfNotNull)
}