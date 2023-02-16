package io.github.sikrinick.geshikt.dsl.component

import io.github.sikrinick.geshikt.dsl.component.layout.Area
import io.github.sikrinick.geshikt.dsl.component.layout.HasSize
import io.github.sikrinick.geshikt.dsl.component.layout.MutableSize
import io.github.sikrinick.geshikt.dsl.component.layout.Rectangle
import io.github.sikrinick.geshikt.dsl.component.layout.ResizableRectangle
import io.github.sikrinick.geshikt.dsl.component.layout.Size
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.types.Type
import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference
import io.github.sikrinick.geshikt.dsl.values.RangeReferencer

sealed interface Line : Container, HasCells

class SingleLine(
    private val modifiable: Modifiable,
    private val childrenList: ChildrenList,
    private val rectangle: ResizableRectangle,
    private val vector: Vector,
    private val axis: Axis
) : Line,
    Area by rectangle,
    HasChildren by childrenList,
    HasCellRangeReference by RangeReferencer(modifiable, rectangle),
    HasModifiers by modifiable
{
    private val parentModifiers = modifiable.propagate()

    // Containers
    override fun row(modifier: Modifier, block: Row.() -> Unit) =
        createContainer(::Row, modifier, block).reference

    override fun column(modifier: Modifier, block: Column.() -> Unit) =
        createContainer(::Column, modifier, block).reference

    private fun <T : Container> createContainer(
        constructor: (Modifiable, ChildrenList, ResizableRectangle) -> T,
        modifier: Modifier,
        block: T.() -> Unit,
    ) = constructor(
        parentModifiers + modifier.toModifiable(),
        ChildrenList(),
        ResizableRectangle(
            position = nextPosition(),
            size = MutableSize(0, 0)
        ),
    ).apply(block).also(::add)

    // Cells
    override fun cell(
        modifier: Modifier,
        size: Size,
        value: Type?,
        cellFormat: CellFormat?
    ) = createCell(
        parentModifiers + modifier.toModifiable(),
        size,
        value,
        cellFormat
    ).also(::add)

    // Repeat cells
    override fun repeatCell(
        times: Int,
        modifier: Modifier,
        size: Size,
        value: Type?,
        cellFormat: CellFormat?
    ) : CellRangeReference {
        require(times > 0) { "repeatCell's times parameter cannot be less than 1" }
        val modifiers = parentModifiers + modifier.toModifiable()
        return RepeatCell(
            times = times,
            axis = axis,
            modifiable = modifiers,
            cell = createCell(modifiers.propagate(), size, value, cellFormat),
        ).also(::add).reference
    }

    // Inner create cell
    private fun createCell(
        modifiable: Modifiable,
        size: Size,
        type: Type?,
        overrideCellFormat: CellFormat?
    ) = Cell(
        modifiable = modifiable,
        area = Rectangle(
            nextPosition(),
            size
        ),
        type,
        overrideCellFormat
    )

    override fun space(times: Int) {
        when(parentModifiers.modifiers.size) {
            0 -> recalculateSize(
                basedOn = LengthyArea(
                    times,
                    axis,
                    base = Rectangle(
                        nextPosition(),
                        Size(1,1)
                    )
                )
            )
            else -> repeatCell(times)
        }
    }

    // Common
    private fun nextPosition() = vector.nextPosition(rectangle)

    private fun add(component: Component) {
        childrenList.children.add(component)
        recalculateSize(basedOn = component)
    }

    private fun recalculateSize(basedOn: HasSize) {
        val newSize = vector.resize(rectangle, basedOn = basedOn)
        rectangle.size.width = newSize.width
        rectangle.size.height = newSize.height
    }
}
