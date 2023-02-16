package io.github.sikrinick.geshikt.dsl.component.style

data class Color(
    val a: Int,
    val r: Int,
    val g: Int,
    val b: Int
) {
    constructor(a: Number, r: Number, g: Number, b: Number) : this(
        a = a.toInt(),
        r = r.toInt(),
        g = g.toInt(),
        b = b.toInt()
    )
    constructor(argb: Long) : this(
        a = (argb and aPos) ushr 24,
        r = (argb and rPos) ushr 16,
        g = (argb and gPos) ushr 8,
        b = (argb and bPos) ushr 0
    )

    /**
     * argb - Hex value string. May or may not start with #
     */
    constructor(argb: String) : this(argb = argb.replaceFirst("#", "").toLong(16))

    companion object {
        private const val aPos = 0xff000000L
        private const val rPos = 0x00ff0000L
        private const val gPos = 0x0000ff00L
        private const val bPos = 0x000000ffL
        const val max = 0xff
    }
}

object StandardColors {
    val black = Color(0x000000)
    val white = Color(0xffffff)
    val blue = Color(0x4285f4)
    val red = Color(0xea4335)
    val yellow = Color(0xfbbc04)
    val green = Color(0x34a853)
    val orange = Color(0xff6d01)
    val lightBlue = Color(0x46bdc6)
    val darkBlue = Color(0x1155cc)

    object Max {
        val red = Color(0xff0000)
        val yellow = Color(0xffff00)
        val green = Color(0x00ff00)
        val lightBlue = Color(0x00ffff)
        val blue = Color(0x0000ff)
        val purple = Color(0xff00ff)
    }
}