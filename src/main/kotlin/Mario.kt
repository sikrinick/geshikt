import io.github.sikrinick.geshikt.dsl.Spreadsheet
import io.github.sikrinick.geshikt.dsl.component.HasCells
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.component.style.StandardColors
import io.github.sikrinick.geshikt.dsl.component.style.colored

fun Spreadsheet.mario() {
    sheet("Mario") {
        // Hat
        row { space(3); red(5) }
        row { space(2); red(9) }
        // Head
        row { space(2); black(3); yellow(2); black(1); yellow(1) }
        row { space(1); black(1); yellow(1); black(1); yellow(3); black(1); yellow(3) }
        row { space(1); black(1); yellow(1); black(2); yellow(3); black(1); yellow(3) }
        row { space(1); black(2); yellow(4); black(4) }
        row { space(3); yellow(7) }
        // Body
        row { space(2); black(2); red(1); black(3) }
        row { space(1); black(3); red(1); black(2); red(1); black(3) }
        row { black(4); red(4); black(4) }
        row { yellow(2); black(1); red(1); yellow(1); red(2); yellow(1); red(1); black(1); yellow(2) }
        row { yellow(3); red(6); yellow(3) }
        row { yellow(2); red(8); yellow(2) }
        row { space(2); red(3); space(2); red(3) }
        // Boots
        row { space(1); black(3); space(4); black(3) }
        row { black(4); space(4); black(4) }
    }
}

private fun HasCells.red(times: Int) = repeatCell(times, Modifier.colored(StandardColors.red))
private fun HasCells.black(times: Int) = repeatCell(times, Modifier.colored(StandardColors.black))
private fun HasCells.yellow(times: Int) = repeatCell(times, Modifier.colored(StandardColors.yellow))
