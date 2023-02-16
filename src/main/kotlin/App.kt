import io.github.sikrinick.geshikt.dsl.title
import io.github.sikrinick.geshikt.runtime.process

fun main() = process {
    spreadsheet(
        // id("put id of an existing sheet here")
        title("Geshikt Example")
    ) {
        movies()
        readmeExamples()
        references()
        mario()
    }
}
