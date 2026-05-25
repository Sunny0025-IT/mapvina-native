package io.github.mapvina.android.testapp.camera;

import io.github.mapvina.android.camera.CameraUpdate;
import io.github.mapvina.android.maps.MapVinaMap;

public class CameraEaseTest extends CameraTest {

  @Override
  void executeCameraMovement(CameraUpdate cameraUpdate, MapVinaMap.CancelableCallback callback) {
    mapvinaMap.easeCamera(cameraUpdate, callback);
  }
}
