package io.github.sikrinick.geshikt.runtime.component

import io.github.sikrinick.geshikt.api.request
import io.github.sikrinick.geshikt.api.setBasicFilter
import io.github.sikrinick.geshikt.dsl.component.HasModifiers
import io.github.sikrinick.geshikt.dsl.component.modifiers.BasicFilter
import io.github.sikrinick.geshikt.dsl.invoke

interface Filtering : HasRequests

class BasicFilter(
    hasModifiers: HasModifiers,
    grid: Grid
) : Filtering {
    private val filterModifier = hasModifiers.modifiers<BasicFilter>()

    override val requests = filterModifier?.let {
        listOf(
            request {
                setBasicFilter {
                    range = grid.range
                }
            }
        )
    } ?: emptyList()
}