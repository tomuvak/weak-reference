package com.tomuvak.weakReference

import com.tomuvak.testing.coroutines.asyncTest
import com.tomuvak.testing.gc.core.tryToAchieveByForcingGc
import kotlin.test.*

class WeakReferenceTest {
    @Test fun weakReferenceReferencesTargetAndTargetIsNotReclaimable() = asyncTest {
        val data = Any()
        val reference = WeakReference(data)
        reference.assertTargetNotReclaimable()
        assertSame(data, assertNotNull(reference.targetOrNull))
    }

    @Test fun weakReferencesTargetIsReclaimable() = asyncTest { generateWeakReference().assertTargetReclaimable() }
    // Needs to be in a separate function so that no strong reference is kept (otherwise fails on some platforms).
    private fun generateWeakReference(): WeakReference<Any> = WeakReference(Any())

    private suspend fun WeakReference<Any>.assertTargetNotReclaimable() = assertFalse(targetIsReclaimable())
    private suspend fun WeakReference<Any>.assertTargetReclaimable() = assertTrue(targetIsReclaimable())
    private suspend fun WeakReference<Any>.targetIsReclaimable(): Boolean =
        tryToAchieveByForcingGc { targetOrNull == null }
}
