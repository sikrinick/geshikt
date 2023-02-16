package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.HasCellRangeReference

class DateDif(
    startDate: Type.Date,
    endDate: Type.Date,
    unit: DateUnit,
) : Type.Formula.ReturnsNumber, Type.Formula by UseFormula(
    "DATEDIF", startDate, endDate, Type.Hardcoded.Text(unit.toString())
)

class ArrayDateDif(
    startDate: Type.Either<Type.Date, Type.Dates>,
    endDate: Type.Either<Type.Date, Type.Dates>,
    unit: DateUnit
) : Type.Formula.ReturnsNumbers, Type.Formula by UseFormula(
    "DATEDIF", startDate, endDate, Type.Hardcoded.Text(unit.value)
)

interface HasDateDif {

    fun datedif(startDate: Type.Date, endDate: Type.Date, unit: DateUnit) =
        DateDif(startDate, endDate, unit)

}

interface HasArrayDateDif {

    fun datedif(startDate: HasCellRangeReference, endDate: HasCellRangeReference, unit: DateUnit) =
        datedif(startDate.reference, endDate.reference, unit)
    fun datedif(startDate: CellRangeReference, endDate: CellRangeReference, unit: DateUnit) =
        ArrayDateDif(
            Type.Either(null, Type.RangeReference.Dates(startDate)),
            Type.Either(null, Type.RangeReference.Dates(endDate)),
            unit
        )
    fun datedif(startDate: CellRangeReference, endDate: Type.Date, unit: DateUnit) =
        ArrayDateDif(
            Type.Either(null, Type.RangeReference.Dates(startDate)),
            Type.Either(endDate),
            unit
        )
}