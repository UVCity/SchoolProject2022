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
    public void parseVenueLatitudeAndLongitudeTest() throws IOException {
        Double[] latitudeAndLongitude = locationData.parseLatitudeAndLongitude(placeNearByStream);
        Assertions.assertEquals(40.189823, latitudeAndLongitude[0]);
        Assertions.assertEquals(-85.419575,latitudeAndLongitude[1]);
    }
}
