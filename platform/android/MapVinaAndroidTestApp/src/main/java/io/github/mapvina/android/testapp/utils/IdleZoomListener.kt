package io.github.mapvina.android.testapp.utils

import android.widget.TextView
import io.github.mapvina.android.maps.MapVinaMap
import io.github.mapvina.android.maps.MapVinaMap.OnCameraIdleListener
import io.github.mapvina.android.testapp.R

class IdleZoomListener(private val mapvinaMap: MapVinaMap, private val textView: TextView) :
    OnCameraIdleListener {
    override fun onCameraIdle() {
        val context = textView.context
        val position = mapvinaMap.cameraPosition
        textView.text = String.format(context.getString(R.string.debug_zoom), position.zoom)
    }
}
