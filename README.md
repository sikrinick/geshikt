# Geshikt — Google Sheets in Kotlin

Geshikt is a declarative API to set up spreadsheets and sheets in Google Sheets.  
[**Link to example in Google Sheets.**](https://docs.google.com/spreadsheets/d/14bNTI4fdFxPiccbd7KJyv5-Yu_00jj7vwQqm2QEbVJU/edit?usp=sharing)


## Getting started

```kotlin
// Add Maven Central if needed
repositories {
    mavenCentral()
}

implementation(group = "io.github.sikrinick", name = "geshikt", version = "0.0.1-SNAPSHOT")
```

## Google Sheets Access

Library uses Google Sheets API.  
- **You have to put `credentials.json` in `src/main/resources` of your application.**  
- It will automatically request for token and store i in `src/main/resources/tokens`.

More info [here](https://developers.google.com/sheets/api/quickstart/java).

## Approach

### Spreadsheet and Sheets

- A single Google Sheets file, which we are working with is called Spreadsheet. It has a title and an ID.  
- Each Spreadsheet consists of Sheets. At least one Sheet always exists in a Spreadsheet.  
- Sheet consists of Rows and Columns.

#### Usage

One may specify Google Sheets spreadsheet to operate with using
- `spreadsheet(id("14bNTI4f...."))` — specify ID, which you can find in URL. Only for existing spreadsheets.
- `spreadsheet(title("Example sheet"))` — specify title. It will either use an existing spreadsheet on your account with
  this name, or it will create new one, if none was found.

```kotlin
import io.github.sikrinick.geshikt.runtime.process
import io.github.sikrinick.geshikt.api.spreadsheet

fun main() = process { 
    spreadsheet(title("Example sheet")) {
        sheet("Example name") {
            row {

            }
            column {

            }
        }
    }
}
```

Each sheet has a name. If no sheet with this name was found — new sheet with this name will be created.

#### Sheet Modifiers

Sheet may have next modifiers
- `hidden` — Hides Sheet in Google Sheets UI.
- `hideGrid` — Hides grid lines.
- `freezeRows` — Number of rows frozen in the grid.
- `freezeColumns` — Number of columns frozen in the grid.
- `hideEmptyRows` — Hides rows to the bottom of working space, which are not used in DSL.
- `hideEmptyColumns` — Hides columns to the right of working space, which are not used in DSL.

```kotlin
sheet(
    "Readme Examples", Sheet.Modifier
        .hideEmptyRows()
        .hideEmptyColumns()
) {
    column {
        
    }
}
```

### Components

- There are 3 basic components: Rows, Columns and Cells.
- There are 2 special components: `repeatCells` and `space`.
- Rows and Columns are not just Components, but Containers.
- Row represents a horizontal vector of positioning, left to right. Column represents a vertical vector of positioning, top to bottom.
- Rows consist of Rows, Columns and Cells. Columns consist of Rows, Columns and Cells.  
- Both Rows and Columns are an abstraction, those do not exist on a Google Sheet.  
- Cell is an atomic entity and is an actual building block.

```kotlin
sheet("Example") {
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
}
```

#### Special cases

There are two special cases, which can be used for optimization purposes:
- `repeatCells` — uses Google Sheets API to repeat specific cell a number of times. 
- `space` — either shifts Container size or uses empty `repeatCells`, if there are any modifiers to propagate.  

### Modifiers

Each component may have modifiers, which inflict visuals, formatting, data filtering and other forms of data visualization.
Those modifiers propagate automatically.
For example, if Row has a modifiers `bold` and `colored(red)`, and contains cells, 
then those cells will be filled with red and text in those cells will be bold.  
In order to disable modifier, child has to specify different value in clear way.  

- `colored` — background color of cell, fill color
- `textColor` — text color
- `bold` — bold text
- `italic` — italic text
- `underline` — text is underlined
- `strikethrough` — text is strikethrough
- `fontSize` — font size
- `font` — text font, can be either picked with `Font` enum, or specified with `Font.Other("Comic Sans MS")`.
- `halign` — horizontal alignment. Can be Left, Center, Right, or Unspecified.
- `valign` — vertical alignment. Can be Top, Middle, Bottom, or Unspecified.
- `wrap` — text wrap strategy. Can be Overflow, Clip, Wrap, or Unspecified.
- `allBorders` — add borders to all cells, both outer and inner. Only solid is supported.
- `outerBorders` — add outer borders to all cells. Only solid is supported.
- `border` — set borders to cell or cell range. 
Can be any combination of left, top, right, bottom, inner vertical and inner horizontal. 
Only solid is supported.
- `basicFilter` — set a basic filter view.  
- `formatBy` — conditional formatting, based on a specific value or a reference.
- `validateOneOf` — data validation. Currently only references are supported.
- `named` — allows to set names for references and ranges, instead of A1-notation.

```kotlin
column {
    cell("Bold red text", Modifier.textColor(StandardColors.red).bold())
}
```

### Cell size

Default cell size is (1,1) — it uses 1 Google Sheets cell in a row an 1 cell in a column.  
One may want to change size. For example, this would make cell to merge 5 Google Sheets cells in a column
and 4 Google Sheets in a row.
```kotlin
column {
    cell("Merged", size = Size(height = 5, width = 4))
}
```

### Cell formats

Currently, there are 4 supported formats:
- Automatic. Default
- Text
- Number
- Date by mask. Default: "dd.mm.yyyy"

It may be useful, when Google Sheets won't be able to set format properly.
For example, in case of dates out of formulas or numbers, which should be used as text. 

```kotlin
column {
    cell(cellFormat = CellFormat.Date()) { // dd.mm.yyyy by default
        join(".", "01", "01", "1991")
    }
    cell(cellFormat = CellFormat.Date("mm/dd/yyyy")) {
        join("/", "02", "23", "1991")
    }
}
```

### Cell values

Cell can be any of next types:
- Text
- Number
- Boolean
- Date
- Formula

```kotlin
column {
    cell("1 + 2") // Text
    val number = cell(1 + 2) // Number
    cell(LocalDate.now()) // Date
    cell(true) // Boolean
    cell { // Formula. Formula block is opened with curly brackets
        sum(number.reference, number.reference)
    }
}
```

#### Formulas

One may use various Google Sheets formulas. 
This is required in case, when created Sheet will be used manually from Google Sheets.  
In this case we may change data in Google Sheets, and it has to be recalculated appropriately.

Currently, at least next formulas are supported.   
Some of those may be used in `arrayformula` context.

Special
- `arrayliteral` (special solution for `{1 \ 2 ; 3 \ 4}`)

Array
- `map`

Date
- `datedif`
- `today`

Google
- `arrayformula`
- `query`

Info
- `isblank`

Logical
- `if`
- `iferror`
- `lambda`
- `or`
- `switch`

Lookup
- `index`

Math
- `countif`
- `countifs`
- `sum`

Operator
- `eq`
- `gt`
- `gte`
- `lt`
- `lte`
- `minus`
- `ne`

Statistical
- `counta`
- `max`

Text
- `char`
- `join`

### References

Every component, both eager and lazy, has a reference, which can be used in formulas or some modifiers.
```kotlin
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
```

### Eager and Lazy

Row, Column and Cell behave eagerly.  
Eager components are added to container during invocation.  
They have corresponding lazy components: LazyRow, LazyColumn and LazyCell.  
In order to add to a grid, those component have to be invoked for a second time.  
However, currently there are some limitations:
- Geshikt does not check if a lazy component was indeed added to a grid.
- All lazy components have to be named.

This is useful when one wants to use component before it is added to a grid.
```kotlin
column {
    val students = lazyColumn("guitar_players") {
        cell("James Hetfield")
        cell("Roman Saenko")
    }
    row {
        cell("Count of guitar players"); cell { counta(students.reference) }
    }
    students()
}
```

<!--
## Utilities
- `valueColumn`

## Extensions

### Custom formulas

### Custom colors

### Additional fonts
-->

## Future plans

### Documentation
- Guide
- `arrayliteral`
- More docs for formulas
- repeatCells 
- space
- Filter view and other special functionality

### Features
- Migration API?
- Non-modifiable fields?
- Data Filler
- Tests

### Quality of life
- Check if value was set for named ranges (substructural type system? linear types?)
- Check if named range with same name was used
- Metadata with stacktraces in DSL?
- Type-based processor. Will it allow to check for used interfaces?

### Optimizations
- Merging Requests (Some Tree structure?)
- Multiple rows in a column = multiple RowData[]
- Multiple columns in a row = multiple (1 RowData with 1 CellData)[]