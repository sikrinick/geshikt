package io.github.sikrinick.geshikt.runtime.component

import io.github.sikrinick.geshikt.dsl.component.Component as UIComponent

class Component(
    val sheetId: Int,
    uiComponent: UIComponent,
    grid: Grid = AreaProcessor(sheetId, uiComponent),
    namedRange: NamedRange = NamedRangeProcessor(uiComponent, grid),
    filtering: Filtering = BasicFilter(uiComponent, grid),
    borders: Borders = BordersProcessor(uiComponent, grid),
    conditionalFormats: ConditionalFormats = ConditionalFormattingProcessor(uiComponent, grid)
) : Grid by grid,
    NamedRange by namedRange,
    HasRequests {

    override val requests = namedRange.requests + filtering.requests + borders.requests + conditionalFormats.requests
}

