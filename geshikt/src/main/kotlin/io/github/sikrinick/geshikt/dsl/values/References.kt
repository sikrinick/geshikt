package io.github.sikrinick.geshikt.dsl.values

import io.github.sikrinick.geshikt.dsl.component.HasModifiers
import io.github.sikrinick.geshikt.dsl.component.layout.Area
import io.github.sikrinick.geshikt.dsl.component.layout.HasPosition
import io.github.sikrinick.geshikt.dsl.component.modifiers.Named
import io.github.sikrinick.geshikt.dsl.invoke

sealed interface Reference

sealed interface CellReference : Reference
sealed interface CellRangeReference : Reference

sealed interface NamedReference : Reference {
    val name: String
}

data class PositionedCellReference(val position: HasPosition) : CellReference
data class NamedCellReference(override val name: String) : CellReference, NamedReference

data class PositionedCellRangeReference(val area: Area) : CellRangeReference
data class NamedCellRangeReference(override val name: String) : CellRangeReference, NamedReference

interface HasCellReference {
    val reference: CellReference
}
interface HasCellRangeReference {
    val reference: CellRangeReference
}

class CellReferencer(modifier: HasModifiers, position: HasPosition) : HasCellReference {
    override val reference: CellReference = when (val named = modifier.modifiers<Named>()) {
        null -> PositionedCellReference(position)
        else -> NamedCellReference(named.name)
    }
}
class RangeReferencer(modifier: HasModifiers, area: Area) : HasCellRangeReference {
    override val reference: CellRangeReference = when (val named = modifier.modifiers<Named>()) {
        null -> PositionedCellRangeReference(area)
        else -> NamedCellRangeReference(named.name)
    }
}
