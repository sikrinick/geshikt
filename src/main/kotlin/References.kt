import io.github.sikrinick.geshikt.dsl.Spreadsheet
import io.github.sikrinick.geshikt.dsl.component.layout.Size
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.component.style.StandardColors
import io.github.sikrinick.geshikt.dsl.component.style.colored
import io.github.sikrinick.geshikt.dsl.component.style.text.*
import io.github.sikrinick.geshikt.dsl.values.invoke
import io.github.sikrinick.geshikt.dsl.values.lazyCell
import io.github.sikrinick.geshikt.dsl.values.lazyColumn
import io.github.sikrinick.geshikt.dsl.values.lazyRow

fun Spreadsheet.references() {
    sheet("Lots of references", boxModifier = Modifier.font(Font.ComicSansMS)) {
        val one = lazyCell(named = "one", value = "1")
        val two = lazyCell(named = "two", value = "2")
        val threeAndFour = lazyRow(named = "threeAndFour") {
            cell(value = "3")
            cell(value = "4")
        }
        val thirteenAndFourteen = lazyColumn("thirteenAndfourteen") {
            cell("13")
            cell("14")
        }

        column(Modifier.colored(StandardColors.red).bold(true)) {
            row(Modifier.strikethrough(true).textColor(StandardColors.darkBlue)) {
                cell(one.reference, Modifier.bold(false).colored(StandardColors.green))
                thirteenAndFourteen()
            }
            row {
                one()
                two()
            }
        }
        column(Modifier.font(Font.Inconsolata)) {
            row(Modifier.fontSize(72)) {
                cell(two.reference, Modifier.textColor(StandardColors.red))
                cell { arrayformula(threeAndFour.reference) }
                space(1)
                cell("5", size = Size(2, 2))
                cell("6")
                cell("7", Modifier.fontSize(60).textColor(StandardColors.red))
            }
            threeAndFour()
        }
        row {
            column {
                cell("8")
                cell("9")
                cell("10")
            }
            column {
                cell("11")
            }
            column {
                cell("12")
                cell {
                    arrayformula(thirteenAndFourteen.reference)
                }
            }
        }
    }
}