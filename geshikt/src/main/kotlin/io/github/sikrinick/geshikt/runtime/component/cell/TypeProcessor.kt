package io.github.sikrinick.geshikt.runtime.component.cell

import io.github.sikrinick.geshikt.dsl.types.LogicalOperator
import io.github.sikrinick.geshikt.dsl.types.Type
import io.github.sikrinick.geshikt.runtime.component.cell.reference.ReferenceProcessor

class TypeProcessor(
    private val referenceProcessor: ReferenceProcessor = ReferenceProcessor()
) {

    fun process(type: Type): String = with(type) {
        when (this) {
            is Type.Hardcoded.Text -> "\"${text}\""
            is Type.Hardcoded.Number -> "$number"
            is Type.Hardcoded.Boolean -> boolean.toString().uppercase()
            is Type.Hardcoded.Date -> "$date"
            is Type.Reference -> referenceProcessor.process(reference)

            is Type.Formula ->
                "$name(${arguments.joinToString(separator = ARGUMENT_SEPARATOR, transform = { process(it) })})"

            is Type.ArrayLiteral ->
                "{" +
                        rows.joinToString(ARGUMENT_SEPARATOR) {
                            it.columns.joinToString(COLUMN_SEPARATOR) { value ->
                                process(value)
                            }
                        } +
                "}"

            is Type.Criterion ->
                "\"${process(operator)}${process(obj)}\""

            is Type.CriterionRange ->
                "\"${process(operator)}${process(objs)}\""

            is Type.Either<*, *> ->
                left?.let(::process) ?: right?.let(::process) ?: throw RuntimeException("Either is empty")
        }
    }

    private fun process(operator: LogicalOperator) = when(operator) {
        LogicalOperator.EqualTo -> "="
        LogicalOperator.NotEqualTo -> "<>"
        LogicalOperator.GreaterThan -> ">"
        LogicalOperator.GreaterThanOrEqualTo -> ">="
        LogicalOperator.LessThan -> "<"
        LogicalOperator.LessThanOrEqualTo -> "<="
    }

    companion object {
        private const val ARGUMENT_SEPARATOR = ";"
        private const val COLUMN_SEPARATOR = "\\" // European localization, comma for US
    }
}
