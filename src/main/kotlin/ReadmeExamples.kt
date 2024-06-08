import io.github.sikrinick.geshikt.dsl.Sheet
import io.github.sikrinick.geshikt.dsl.Spreadsheet
import io.github.sikrinick.geshikt.dsl.component.CellFormat
import io.github.sikrinick.geshikt.dsl.component.layout.Size
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.component.style.StandardColors
import io.github.sikrinick.geshikt.dsl.component.style.text.bold
import io.github.sikrinick.geshikt.dsl.component.style.text.textColor
import io.github.sikrinick.geshikt.dsl.values.invoke
import io.github.sikrinick.geshikt.dsl.values.lazyColumn
import java.time.LocalDate

fun Spreadsheet.readmeExamples() = sheet(
    "Readme Examples", Sheet.Modifier
        .hideEmptyRows()
        .hideEmptyColumns()
) {
    column {
        column {
            column {
                cell(1)
            }
            row {
                cell(2)
            }
            cell(3)
        }
        row {
            cell(4)
            row {
                cell(5)
            }
            column {
                cell(6)
            }
        }

        space(1)

        column {
            cell("Bold red text", Modifier.textColor(StandardColors.red).bold())
        }

        space(1)

        column {
            cell("Merged", size = Size(height = 5, width = 4))
        }

        space(1)

        column {
            cell(cellFormat = CellFormat.Date()) { // dd.mm.yyyy by default
                join(".", "01", "01", "1991")
            }
            cell(cellFormat = CellFormat.Date("mm/dd/yyyy")) {
                join("/", "02", "23", "1991")
            }
        }

        space(1)

        column {
            cell("1 + 2") // Text
            val number = cell(1 + 2) // Number
            cell(LocalDate.now()) // Date
            cell(true) // Boolean
            cell {
                sum(number.reference, number.reference)
            }
        }

        space(1)

        column {
            val a = cell(1)
            val b = cell(2)
            cell {
                sum(a.reference, b.reference)
            }
            // or
            cell {
                a.reference + b.reference
            }
        }

        space(1)

        column {
            val students = lazyColumn("guitar_players") {
                cell("James Hetfield")
                cell("Carlos Santana")
            }
            row {
                cell("Count of guitar players"); cell { counta(students.reference) }
            }
            students()
        }
    }
}