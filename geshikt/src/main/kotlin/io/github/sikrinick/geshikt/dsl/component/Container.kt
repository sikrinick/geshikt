package io.github.sikrinick.geshikt.dsl.component

import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference

interface Container : Component, HasChildren, HasRows, HasColumns, HasCellRangeReference

interface HasChildren {
    val children: List<Component>
}

data class ChildrenList(
    override val children: MutableList<Component> = mutableListOf()
) : HasChildren

