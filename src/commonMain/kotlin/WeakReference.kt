package com.tomuvak.weakReference

/**
 * A weak reference to the given [target]. The weak reference might be used to _try_ and regain a normal (= strong)
 * reference to the target through [targetOrNull], which will return the original target if it has not been reclaimed
 * by garbage collection (something a weak reference does not prevent), or `null` if it has.
 *
 * Note: the given [target] must be a reference to a "proper" object, not a primitive value or string. Passing a
 * primitive value or string results in undefined run-time behavior, including possibly thrown exceptions, depending on
 * the specific type and platform.
 */
expect class WeakReference<out T : Any>(target: T) { val targetOrNull: T? }
