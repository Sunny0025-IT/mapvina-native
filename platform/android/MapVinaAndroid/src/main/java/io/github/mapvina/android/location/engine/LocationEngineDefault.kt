package io.github.mapvina.android.location.engine

import android.content.Context

object LocationEngineDefault {
    /**
     * Returns the default `LocationEngine`.
     */
    fun getDefaultLocationEngine(context: Context): LocationEngine {
        return LocationEngineProxy(
            MapVinaFusedLocationEngineImpl(
                context.applicationContext
            )
        )
    }
}
