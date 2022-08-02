package com.tomuvak.weakReference

private external class WeakRef<T>(target: T) { fun deref(): T? }

actual class WeakReference<out T : Any> actual constructor(target: T) {
    private val underlying: WeakRef<T> = WeakRef(target)
    actual val targetOrNull: T? get() = underlying.deref()
}
