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
sealed interface PositionedCellReference : CellReference {
    val position: HasPosition
}
sealed interface PositionedCellRangeReference : CellRangeReference {
    val area: Area
}

data class OnlyPositionedCellReference(override val position: HasPosition) : CellReference, PositionedCellReference
data class NamedCellReference(override val name: String) : CellReference, NamedReference
data class BothNamedAndPositionedCellReference(
    override val position: HasPosition,
    override val name: String
) : CellReference, NamedReference, PositionedCellReference

data class OnlyPositionedCellRangeReference(override val area: Area) : CellRangeReference, PositionedCellRangeReference
data class NamedCellRangeReference(override val name: String) : CellRangeReference, NamedReference
data class BothNamedAndPositionedCellRangeReference(
    override val name: String,
    override val area: Area
) : CellRangeReference, NamedReference, PositionedCellRangeReference

interface HasCellReference {
    val reference: PositionedCellReference
}
interface HasCellRangeReference {
    val reference: PositionedCellRangeReference
}

class CellReferencer(modifier: HasModifiers, position: HasPosition) : HasCellReference {
    override val reference = when (val named = modifier.modifiers<Named>()) {
        null -> OnlyPositionedCellReference(position)
        else -> BothNamedAndPositionedCellReference(name = named.name, position = position)
    }
}
class RangeReferencer(modifier: HasModifiers, area: Area) : HasCellRangeReference {
    override val reference: PositionedCellRangeReference = when (val named = modifier.modifiers<Named>()) {
        null -> OnlyPositionedCellRangeReference(area)
        else -> BothNamedAndPositionedCellRangeReference(named.name, area)
    }
}
