package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

interface HasArrayLiteral {

    fun arrayliteral(
        builder: Builder.() -> Unit
    ) = Builder().apply(builder).build()

    class Builder {
        val rows = mutableListOf<Row>()

        fun rows(vararg values: Type) = values.forEach { row { col(it) } }
        fun row(block: Row.() -> Unit) {
            Row().apply(block).also(rows::add)
        }

        class Row : WorksWithFormulas {
            val columns = mutableListOf<Type>()

            fun col(cellReference: CellReference) = col(cellReference.type())
            fun col(cellRangeReference: CellRangeReference) = col(cellRangeReference.type())
            fun columns(vararg values: Type) = values.forEach(::col)
            fun col(value: Type) {
                columns.add(value)
            }
        }

        fun build() = Type.ArrayLiteral(
            rows = rows.map { Type.ArrayLiteral.Row(columns = it.columns) }
        )
    }
}
