package edu.bsu.cs222.model;

import edu.bsu.cs222.AddressParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class PlaceFromTextTest {
    private final InputStream placeFromTextStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("FindPlaceFromTextData.json");
    private final AddressParser parser = new AddressParser();
    @Test
    public void parseUserAddressTest() throws IOException {
        String address = parser.parseUserAddress(placeFromTextStream);
        Assertions.assertEquals("2720 W Jackson St, Muncie, IN 47303, USA", address);
    }
    @Test
    public void parseUserLatitudeAndLongitudeTest() throws IOException {
        Double[] latitudeAndLongitude = parser.parseLatitudeAndLongitude(placeFromTextStream);
        Assertions.assertEquals(40.1934735, latitudeAndLongitude[0]);
        Assertions.assertEquals(-85.4186142, latitudeAndLongitude[1]);
    }
}
