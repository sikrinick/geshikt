package io.github.sikrinick.geshikt.runtime.component.cell.reference

import io.github.sikrinick.geshikt.dsl.values.NamedReference
import io.github.sikrinick.geshikt.dsl.values.PositionedCellRangeReference
import io.github.sikrinick.geshikt.dsl.values.PositionedCellReference
import io.github.sikrinick.geshikt.dsl.values.Reference

class ReferenceProcessor(
    private val positionMapper: PositionMapper = PositionMapper(),
    private val areaMapper: AreaMapper = AreaMapper(positionMapper)
) {

    fun process(reference: Reference) = when(reference) {
        is NamedReference -> reference.name
        is PositionedCellReference -> positionMapper.toA1Notation(reference.position)
        is PositionedCellRangeReference -> areaMapper.toA1Notation(reference.area)
    }
}
