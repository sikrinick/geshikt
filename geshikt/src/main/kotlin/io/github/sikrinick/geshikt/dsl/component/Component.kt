package io.github.sikrinick.geshikt.dsl.component

import io.github.sikrinick.geshikt.dsl.component.layout.Area

@DslMarker
annotation class ComponentMarker

@ComponentMarker
sealed interface Component : Area, HasModifiers