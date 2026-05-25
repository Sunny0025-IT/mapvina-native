package io.github.mapvina.android.testapp.geometry;

import static org.junit.Assert.assertEquals;

import io.github.mapvina.android.camera.CameraUpdateFactory;
import io.github.mapvina.android.geometry.LatLng;
import io.github.mapvina.android.geometry.LatLngBounds;
import io.github.mapvina.android.testapp.action.MapVinaMapAction;
import io.github.mapvina.android.testapp.activity.BaseTest;
import io.github.mapvina.android.testapp.activity.feature.QueryRenderedFeaturesBoxHighlightActivity;
import io.github.mapvina.android.testapp.utils.TestConstants;

import org.junit.Test;

/**
 * Instrumentation test to validate integration of LatLngBounds
 */
public class LatLngBoundsTest extends BaseTest {

  private static final double MAP_BEARING = 50;

  @Override
  protected Class getActivityClass() {
    return QueryRenderedFeaturesBoxHighlightActivity.class;
  }

  @Test
  public void testLatLngBounds() {
    // regression test for #9322
    validateTestSetup();
    MapVinaMapAction.invoke(mapvinaMap, (uiController, mapvinaMap) -> {
      LatLngBounds bounds = new LatLngBounds.Builder()
        .include(new LatLng(48.8589506, 2.2773457))
        .include(new LatLng(47.2383171, -1.6309316))
        .build();
      mapvinaMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
    });
  }

  @Test
  public void testLatLngBoundsBearing() {
    // regression test for #12549
    validateTestSetup();
    MapVinaMapAction.invoke(mapvinaMap, (uiController, mapvinaMap) -> {
      LatLngBounds bounds = new LatLngBounds.Builder()
        .include(new LatLng(48.8589506, 2.2773457))
        .include(new LatLng(47.2383171, -1.6309316))
        .build();
      mapvinaMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
      mapvinaMap.moveCamera(CameraUpdateFactory.bearingTo(MAP_BEARING));
      assertEquals(
        "Initial bearing should match for latlngbounds",
        mapvinaMap.getCameraPosition().bearing,
        MAP_BEARING,
        TestConstants.BEARING_DELTA
      );

      mapvinaMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
      assertEquals("Bearing should match after resetting latlngbounds",
        mapvinaMap.getCameraPosition().bearing,
        MAP_BEARING,
        TestConstants.BEARING_DELTA);
    });
  }

}
