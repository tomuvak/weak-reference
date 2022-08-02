package com.tomuvak.weakReference

actual class WeakReference<out T : Any> actual constructor(target: T) {
    private val underlying: java.lang.ref.WeakReference<T> = java.lang.ref.WeakReference(target)
    actual val targetOrNull: T? get() = underlying.get()
}
