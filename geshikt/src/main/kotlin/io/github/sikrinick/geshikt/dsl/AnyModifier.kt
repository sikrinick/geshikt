package io.github.sikrinick.geshikt.dsl

import io.github.sikrinick.geshikt.dsl.component.modifiers.Modifier
import kotlin.reflect.KClass

interface AnyModifier<in T : AnyModifier<T>> {
    val root: AnyModifier<T>?

    fun unfold(): Modifiers<T> = buildMap {
        this@AnyModifier.takeUnless { it == Modifier.None }?.let { put(it) }
    }
}

typealias MutableModifiers<T> = MutableMap<KClass<out AnyModifier<T>>, AnyModifier<T>>
private fun <T : AnyModifier<T>> MutableModifiers<T>.put(modifier: AnyModifier<T>) {
    putIfAbsent(modifier::class, modifier)
    modifier.root?.let { put(it) }
}

typealias Modifiers<T> = Map<out KClass<out AnyModifier<T>>, AnyModifier<T>>

inline operator fun <reified T : AnyModifier<T>> Modifiers<in T>.invoke() = get(T::class) as T?