package edu.bsu.cs222.model;

import edu.bsu.cs222.Model.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;

public class CoordinatesTest {

    private final InputStream placeNearByStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("PlaceNearBySearchData.json");
    private final Coordinates locationData = new Coordinates();

    @Test
    public void parseLatLngTest() throws IOException {
        locationData.parseLatLng(placeNearByStream);
        Assertions.assertEquals(40.189823, locationData.latitude);
        Assertions.assertEquals(-85.419575,locationData.longitude);
    }

    @Test
    public void findCoordinatesMidpointTest() {
        Coordinates c1 = new Coordinates();
        Coordinates c2 = new Coordinates();
        c1.latitude = 50.0;
        c1.longitude = 100.0;
        c2.latitude = 0.0;
        c2.longitude = 0.0;
        locationData.findCoordinatesMidpoint(c1, c2);
        Assertions.assertEquals(25.0 ,locationData.latitude);
        Assertions.assertEquals(50.0, locationData.longitude);
    }
    @Test
    public void splitCoordinatesTest(){

    }
}
