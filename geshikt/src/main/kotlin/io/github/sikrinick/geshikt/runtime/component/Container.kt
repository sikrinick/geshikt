package io.github.sikrinick.geshikt.runtime.component

import io.github.sikrinick.geshikt.dsl.component.Container as UIContainer

class Container(
    component: Component,
    uiContainer: UIContainer,
    hasChildren: HasChildren = ChildrenProcessor(component.sheetId, uiContainer)
) : HasChildren by hasChildren,
    HasRequests {
    override val requests = component.requests + hasChildren.requests
}