package com.tomuvak.weakReference

actual class WeakReference<out T : Any> actual constructor(target: T) {
    private val underlying: kotlin.native.ref.WeakReference<T> = kotlin.native.ref.WeakReference(target)
    actual val targetOrNull: T? get() = underlying.get()
}
