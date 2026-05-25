package io.github.mapvina.android

import io.github.mapvina.android.testapp.MapVinaApplication

class InstrumentationApplication : MapVinaApplication() {
    fun initializeLeakCanary(): Boolean {
        // do not initialize leak canary during instrumentation tests
        return true
    }
}
