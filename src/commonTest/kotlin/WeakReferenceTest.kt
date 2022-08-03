package com.tomuvak.weakReference

import com.tomuvak.testing.coroutines.asyncTest
import com.tomuvak.testing.gc.core.tryToAchieveByForcingGc
import kotlin.test.*

class WeakReferenceTest {
    @Test fun weakReference() = asyncTest { generateAndVerifyWeakReference().assertTargetReclaimable() }

    private suspend fun generateAndVerifyWeakReference(): WeakReference<Any> {
        val data = Any()
        val reference = WeakReference(data)
        reference.assertTargetNotReclaimable()
        assertSame(data, assertNotNull(reference.targetOrNull))
        return reference
    }

    private suspend fun WeakReference<Any>.assertTargetNotReclaimable() = assertFalse(targetIsReclaimable())
    private suspend fun WeakReference<Any>.assertTargetReclaimable() = assertTrue(targetIsReclaimable())
    private suspend fun WeakReference<Any>.targetIsReclaimable(): Boolean =
        tryToAchieveByForcingGc { targetOrNull == null }
}
