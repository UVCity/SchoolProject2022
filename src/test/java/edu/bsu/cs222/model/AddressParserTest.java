package edu.bsu.cs222.model;

import edu.bsu.cs222.Model.AddressParser;
import edu.bsu.cs222.Model.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class AddressParserTest {
    private final InputStream placeFromTextStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("FindPlaceFromTextData.json");
    private final AddressParser parser = new AddressParser();
    private final Coordinates locationData = new Coordinates();

    @Test
    void parseAddressValidation() throws IOException {
        boolean result = parser.parseResultStatus(placeFromTextStream);
        Assertions.assertTrue(result);
    }
    @Test
    public void parseUserAddressTest() throws IOException {
        String address = parser.parseUserAddress(placeFromTextStream);
        Assertions.assertEquals("2720 W Jackson St, Muncie, IN 47303, USA", address);
    }
    @Test
    public void parseUserLatitudeAndLongitudeTest() throws IOException {
        Double[] latitudeAndLongitude = locationData.parseLatitudeAndLongitude(placeFromTextStream);
        Assertions.assertEquals(40.1934735, latitudeAndLongitude[0]);
        Assertions.assertEquals(-85.4186142, latitudeAndLongitude[1]);
    }
}
