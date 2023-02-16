package io.github.sikrinick.geshikt.runtime.component

import io.github.sikrinick.geshikt.api.addNamedRange
import io.github.sikrinick.geshikt.api.deleteNamedRange
import io.github.sikrinick.geshikt.api.request
import io.github.sikrinick.geshikt.dsl.component.HasModifiers
import io.github.sikrinick.geshikt.dsl.component.modifiers.Named
import io.github.sikrinick.geshikt.dsl.invoke

interface NamedRange : HasRequests {
    val name: String?
}
class NamedRangeProcessor(
    hasModifiers: HasModifiers,
    grid: Grid
) : NamedRange {
    private val namedModifier = hasModifiers.modifiers<Named>()

    override val name = namedModifier?.name
    override val requests = name?.let {
        listOf(
            request {
                deleteNamedRange(it)
            },
            request {
                addNamedRange {
                    name = it
                    namedRangeId = it
                    range = grid.range
                }
            }
        )
    } ?: emptyList()
}