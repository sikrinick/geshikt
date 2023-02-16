import io.github.sikrinick.geshikt.dsl.Spreadsheet
import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import io.github.sikrinick.geshikt.dsl.component.modifiers.allBorders
import io.github.sikrinick.geshikt.dsl.component.style.HAlign
import io.github.sikrinick.geshikt.dsl.component.style.StandardColors
import io.github.sikrinick.geshikt.dsl.component.style.VAlign
import io.github.sikrinick.geshikt.dsl.component.style.Wrap
import io.github.sikrinick.geshikt.dsl.component.style.colored
import io.github.sikrinick.geshikt.dsl.component.style.halign
import io.github.sikrinick.geshikt.dsl.component.style.text.Font
import io.github.sikrinick.geshikt.dsl.component.style.text.bold
import io.github.sikrinick.geshikt.dsl.component.style.text.font
import io.github.sikrinick.geshikt.dsl.component.style.text.textColor
import io.github.sikrinick.geshikt.dsl.component.style.valign
import io.github.sikrinick.geshikt.dsl.component.style.wrap


class Movie(
    val title: String,
    val year: Int,
    vararg val genres: Genre
) {
    enum class Genre(val genreName: String) {
        Drama("Drama"),
        Crime("Crime"),
        Biography("Biography"),
        History("History"),
        Action("Action"),
        Adventure("Adventure"),
        SciFi("Sci-Fi"),
    }
}

val movies = listOf(
    Movie("The Shawshank Redemption", 1994, Movie.Genre.Drama),
    Movie("The Godfather", 1972, Movie.Genre.Crime, Movie.Genre.Drama),
    Movie("Schindler's List", 1993, Movie.Genre.Biography, Movie.Genre.Drama, Movie.Genre.History),
    Movie("The Dark Knight", 2008, Movie.Genre.Action, Movie.Genre.Crime, Movie.Genre.Drama),
    Movie("12 Angry Men", 1957, Movie.Genre.Crime, Movie.Genre.Drama),
    Movie("The Godfather Part II", 1974, Movie.Genre.Crime, Movie.Genre.Drama),
    Movie("The Lord of the Rings: The Return of the King", 2003, Movie.Genre.Action, Movie.Genre.Adventure, Movie.Genre.Drama),
    Movie("Pulp Fiction", 1994, Movie.Genre.Crime, Movie.Genre.Drama),
    Movie("Inception", 2010, Movie.Genre.Action, Movie.Genre.Adventure, Movie.Genre.SciFi),
    Movie("Fight Club", 1999, Movie.Genre.Drama),
)

fun Spreadsheet.movies() {
    basicExample()
    advancedExample()
}

private fun Spreadsheet.basicExample() {
    sheet("Movies. Basic example") {

        column {
            cell("Example #1. IMDB Top 10 Movie list", Modifier.bold())
            column {
                row {
                    cell("Movie"); cell("Year"); cell("Genres")
                }
                movies.forEach {
                    row {
                        cell(it.title)
                        cell(it.year)
                        cell(it.genres.joinToString(separator = ", ", transform = { it.genreName }))
                    }
                }
            }
            space(2)

            cell("Example #2. Now with fonts and bold header", Modifier.bold())
            column {
                row(Modifier.bold()) {
                    cell("Movie", Modifier.font(Font.Arial));
                    cell("Year", Modifier.font(Font.TimesNewRoman));
                    cell("Genres", Modifier.font(Font.Inconsolata))
                }
                movies.forEach {
                    row {
                        cell(it.title, Modifier.font(Font.Arial))
                        cell(it.year, Modifier.font(Font.TimesNewRoman))
                        cell(
                            it.genres.joinToString(separator = ", ", transform = { it.genreName }),
                            Modifier.font(Font.Inconsolata)
                        )
                    }
                }
            }
            space(2)

            cell("Example #3. Let's add some background color, text colors and borders", Modifier.bold())
            column(Modifier.allBorders()) {
                row(Modifier.bold().colored(StandardColors.black).textColor(StandardColors.white).allBorders()) {
                    cell("Movie", Modifier.font(Font.Arial));
                    cell("Year", Modifier.font(Font.TimesNewRoman));
                    cell("Genres", Modifier.font(Font.Inconsolata))
                }
                movies.forEach {
                    row {
                        cell(it.title, Modifier.font(Font.Arial).textColor(StandardColors.red))
                        cell(it.year, Modifier.font(Font.TimesNewRoman).textColor(StandardColors.darkBlue))
                        cell(
                            it.genres.joinToString(separator = ", ", transform = { it.genreName }),
                            Modifier.font(Font.Inconsolata)
                        )
                    }
                }
            }
            space(2)

            cell(
                "Example #4. Centered horizontal alignment for headers, wrap and middle vertical alignment for values",
                Modifier.bold()
            )
            column(Modifier.allBorders()) {
                row(
                    Modifier
                    .bold()
                    .colored(StandardColors.black)
                    .textColor(StandardColors.white)
                    .allBorders()
                    .halign(HAlign.Type.Center)
                ) {
                    cell("Movie", Modifier.font(Font.Arial));
                    cell("Year", Modifier.font(Font.TimesNewRoman));
                    cell("Genres", Modifier.font(Font.Inconsolata))
                }
                movies.forEach {
                    row(Modifier.wrap(Wrap.Type.Wrap).valign(VAlign.Type.Middle)) {
                        cell(it.title, Modifier.font(Font.Arial).textColor(StandardColors.red))
                        cell(it.year, Modifier.font(Font.TimesNewRoman).textColor(StandardColors.darkBlue))
                        cell(
                            it.genres.joinToString(separator = ", ", transform = { it.genreName }),
                            Modifier.font(Font.Inconsolata)
                        )
                    }
                }
            }
            space(2)
        }
    }
}

fun Spreadsheet.advancedExample() {
    sheet("Movies. Advanced example") {
        column {
            val a = cell(1)
            val b = cell(2)
            cell {
                sum(a.reference, b.reference)
            }
            cell {
                a.reference + b.reference
            }
        }
    }
}