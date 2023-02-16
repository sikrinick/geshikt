package io.github.sikrinick.geshikt.runtime.component

import com.google.api.services.sheets.v4.model.UpdateBordersRequest
import io.github.sikrinick.geshikt.api.border
import io.github.sikrinick.geshikt.api.request
import io.github.sikrinick.geshikt.api.updateBorders
import io.github.sikrinick.geshikt.dsl.component.HasModifiers
import io.github.sikrinick.geshikt.dsl.component.modifiers.Border
import io.github.sikrinick.geshikt.dsl.component.style.StandardColors
import io.github.sikrinick.geshikt.dsl.invoke

interface Borders : HasRequests

class BordersProcessor(
    hasModifiers: HasModifiers,
    grid: Grid,
    private val colorMapper: ColorMapper = ColorMapper()
) : Borders {
    private val borderModifier = hasModifiers.modifiers<Border>()

    override val requests = borderModifier?.let {
        listOf(
            request {
                updateBorders {
                    if (it.top == true) top = solidBorder
                    if (it.left == true) left = solidBorder
                    if (it.right == true) right = solidBorder
                    if (it.bottom == true) bottom = solidBorder
                    if (it.innerVertical == true) innerVertical = solidBorder
                    if (it.innerHorizontal == true) innerHorizontal = solidBorder

                    range = grid.range
                }
            }
        )
    } ?: emptyList()

    private val UpdateBordersRequest.solidBorder get() = border {
        colorStyle = colorMapper.map(StandardColors.black)
        width = 1
        style = Styles.SOLID.value
    }

    private enum class Styles(val value: String) {
        SOLID("SOLID")
    }
}