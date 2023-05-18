package io.github.sikrinick.geshikt.dsl.types

import io.github.sikrinick.geshikt.dsl.values.CellRangeReference
import io.github.sikrinick.geshikt.dsl.values.CellReference

class If(
    condition: Type.Boolean,
    ifTrue: Type.Singular,
    ifFalse: Type.Singular
) : Type.Formula.ReturnsAny, Type.Formula by UseFormula(
    "IF", condition, ifTrue, ifFalse
)

class ArrayIf(
    conditions: Type.Either<Type.Boolean, Type.Booleans>,
    ifTrue: Type,
    ifFalse: Type
) : Type.Formula.ReturnsAnys {

    constructor(condition: Type.Boolean, ifTrue: Type, ifFalse: Type) :
            this(Type.Either(condition, null), ifTrue, ifFalse)

    constructor(conditions: Type.Booleans, ifTrue: Type, ifFalse: Type) :
            this(Type.Either(null, conditions), ifTrue, ifFalse)

    override val name = "IF"
    override val arguments = arrayOf(conditions, ifTrue, ifFalse)
}

interface HasIf : WorksWithFormulas {

    fun `if`(condition: Type.Boolean, ifTrue: String, ifFalse: String) =
        If(condition, ifTrue.type(), ifFalse.type())

    fun `if`(condition: Type.Boolean, ifTrue: CellReference, ifFalse: String) =
        If(condition, ifTrue.type(), ifFalse.type())

    fun `if`(condition: Type.Boolean, ifTrue: CellReference, ifFalse: CellReference) =
        If(condition, ifTrue.type(), ifFalse.type())

    fun `if`(condition: Type.Boolean, ifTrue: Type.Singular, ifFalse: String) =
        If(condition, ifTrue, ifFalse.type())

    fun `if`(condition: Type.Boolean, ifTrue: String, ifFalse: Type.Singular) =
        If(condition, ifTrue.type(), ifFalse)

    fun `if`(condition: Type.Boolean, ifTrue: Type.Singular, ifFalse: Type.Singular) =
        If(condition, ifTrue, ifFalse)
}

interface HasArrayIf : WorksWithFormulas {

    fun `if`(conditions: Type.Booleans, ifTrue: String, ifFalse: String) =
        ArrayIf(conditions, ifTrue.type(), ifFalse.type())

    fun `if`(conditions: Type.Booleans, ifTrue: CellReference, ifFalse: String) =
        ArrayIf(conditions, ifTrue.type(), ifFalse.type())

    fun `if`(conditions: Type.Booleans, ifTrue: CellReference, ifFalse: CellReference) =
        ArrayIf(conditions, ifTrue.type(), ifFalse.type())

    fun `if`(condition: Type.Boolean, ifTrue: String, ifFalse: Type.Range) =
        ArrayIf(condition, ifTrue.type(), ifFalse)
    fun `if`(conditions: Type.Booleans, ifTrue: String, ifFalse: Type) =
        ArrayIf(conditions, Type.Hardcoded.Text(ifTrue), ifFalse)

    fun `if`(condition: Type.Boolean, ifTrue: CellReference, ifFalse: Type.Range) =
        ArrayIf(condition, Type.CellReference.Text(ifTrue), ifFalse)
    fun `if`(conditions: Type.Booleans, ifTrue: CellReference, ifFalse: Type) =
        ArrayIf(conditions, Type.CellReference.Text(ifTrue), ifFalse)

    fun `if`(condition: Type.Boolean, ifTrue: Type.Singular, ifFalse: Type.Range) =
        ArrayIf(condition, ifTrue, ifFalse)
    fun `if`(condition: Type.Boolean, ifTrue: Type.Range, ifFalse: Type.Singular) =
        ArrayIf(condition, ifTrue, ifFalse)
    fun `if`(conditions: Type.Booleans, ifTrue: Type, ifFalse: Type) =
        ArrayIf(conditions, ifTrue, ifFalse)


    fun `if`(condition: Type.Boolean, ifTrue: Type.Range, ifFalse: String) =
        ArrayIf(condition, ifTrue, Type.Hardcoded.Text(ifFalse))
    fun `if`(conditions: Type.Booleans, ifTrue: Type, ifFalse: String) =
        ArrayIf(conditions, ifTrue, Type.Hardcoded.Text(ifFalse))

    fun `if`(condition: Type.Boolean, ifTrue: String, ifFalse: CellRangeReference) =
        ArrayIf(condition, ifTrue.type(), ifFalse.type())
    fun `if`(conditions: Type.Booleans, ifTrue: String, ifFalse: CellRangeReference) =
        ArrayIf(conditions, Type.Hardcoded.Text(ifTrue), Type.RangeReference.Anys(ifFalse))

    fun `if`(condition: Type.Boolean, ifTrue: CellReference, ifFalse: CellRangeReference) =
        ArrayIf(condition, ifTrue.type(), Type.RangeReference.Anys(ifFalse))

}
