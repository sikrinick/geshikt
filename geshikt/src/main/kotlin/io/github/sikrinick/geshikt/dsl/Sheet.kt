package io.github.sikrinick.geshikt.dsl

import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier as BoxModifier
import io.github.sikrinick.geshikt.dsl.component.Box
import io.github.sikrinick.geshikt.dsl.component.ChildrenList
import io.github.sikrinick.geshikt.dsl.component.Container
import io.github.sikrinick.geshikt.dsl.component.layout.MutableSize
import io.github.sikrinick.geshikt.dsl.component.layout.Position
import io.github.sikrinick.geshikt.dsl.component.layout.ResizableRectangle

@JvmInline value class SheetTitle(val value: String)

class Sheet internal constructor(
    title: SheetTitle,
    internal val modifier: Modifier = Modifier.None,
    private val boxModifier: BoxModifier = BoxModifier.None,
) : Container by Box(
    modifiable = boxModifier.toModifiable(),
    childrenList = ChildrenList(),
    rectangle = ResizableRectangle(
        position = Position(
            title = title,
            startX = 0,
            startY = 0
        ),
        size = MutableSize(
            width = 0,
            height = 0
        )
    )
) {
    val sheetModifiers = modifier.unfold()

    sealed class Modifier(override val root: Modifier? = null) : AnyModifier<Modifier> {

        class Hidden(val hidden: Boolean, root: Modifier? = null) : Modifier(root)
        class HideGrid(val hideGrid: Boolean, root: Modifier? = null) : Modifier(root)
        class FrozenRows(val count: Int, root: Modifier? = null) : Modifier(root)
        class FrozenColumns(val count: Int, root: Modifier? = null) : Modifier(root)
        class HideEmptyRows(root: Modifier? = null) : Modifier(root)
        class HideEmptyColumns(root: Modifier? = null) : Modifier(root)
        internal object None : Modifier(null)

        fun hidden(hidden: Boolean) = hidden(hidden, this)
        fun hideGrid(hideGrid: Boolean) = hideGrid(hideGrid, this)
        fun freezeRows(count: Int) = freezeRows(count, this)
        fun freezeColumns(count: Int) = freezeColumns(count, this)
        fun hideEmptyRows() = hideEmptyRows(this)
        fun hideEmptyColumns() = hideEmptyColumns(this)

        companion object {
            fun hidden(hidden: Boolean, root: Modifier? = null) = Hidden(hidden, root)
            fun hideGrid(hideGrid: Boolean, root: Modifier? = null) = HideGrid(hideGrid, root)
            fun freezeRows(count: Int, root: Modifier? = null) = FrozenRows(count, root)
            fun freezeColumns(count: Int, root: Modifier? = null) = FrozenColumns(count, root)
            fun hideEmptyRows(root: Modifier? = null) = HideEmptyRows(root)
            fun hideEmptyColumns(root: Modifier? = null) = HideEmptyColumns(root)
        }
    }
}
