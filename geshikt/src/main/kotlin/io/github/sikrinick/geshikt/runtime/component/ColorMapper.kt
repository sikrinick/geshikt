package io.github.sikrinick.geshikt.runtime.component

import io.github.sikrinick.geshikt.dsl.component.style.Color as UIColor
import io.github.sikrinick.geshikt.api.color

class ColorMapper {
    private val maxFloat = UIColor.max.toFloat()

    fun map(uiColor: UIColor) = with(uiColor) {
        color {
            alpha = a.toGoogleSheetColorFraction()
            red = r.toGoogleSheetColorFraction()
            green = g.toGoogleSheetColorFraction()
            blue = b.toGoogleSheetColorFraction()
        }
    }

    private fun Int.toGoogleSheetColorFraction() = this / maxFloat
}