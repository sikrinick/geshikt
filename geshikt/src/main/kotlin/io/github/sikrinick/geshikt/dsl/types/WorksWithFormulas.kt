package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

interface WorksWithFormulas {

    fun String.type() = Type.Hardcoded.Text(this)
    fun Number.type() = Type.Hardcoded.Number(this)
    fun CellReference.type() = Type.CellReference.Any(this)
    fun CellRangeReference.type() = Type.RangeReference.Anys(this)

    fun <L : Type.Singular, R : Type.Range> L.either(): Type.Either<L, R> = Type.Either(this, null)
    fun <L : Type.Singular, R : Type.Range> R.either(): Type.Either<L, R> = Type.Either(null, this)
}