package edu.bsu.cs222.model;

import edu.bsu.cs222.AddressParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class LetsLynkTests {

    public final InputStream placeFromTextStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("FindPlaceFromTextData.json");
    public final InputStream placeDirectionsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Directions.json");
    public final AddressParser parser = new AddressParser();

    @Test
    public void ParseUserAddressTest() throws IOException {
        String address = parser.parseUserAddress(placeFromTextStream);
        Assertions.assertEquals("2720 W Jackson St, Muncie, IN 47303, USA",address);
    }
    @Test
    public void ParseUserLongitude() throws IOException {
        String longitude = parser.parseLongitude(placeFromTextStream);
        Assertions.assertEquals("-85.4186142",longitude);
    }
    @Test
    public void ParseUserLatitudeTest() throws IOException {
        String latitude = parser.parseLatitude(placeFromTextStream);
        Assertions.assertEquals("40.1934735", latitude);
    }
    @Test
    public void ParseDistance() throws IOException {
        String distance = parser.parseDistanceToAddress(placeDirectionsStream);
        Assertions.assertEquals("0.3 mi", distance);
    }
    //________________U_R_L___T_E_S_T_I_N_G______________
    @Test
    public void ParseUsersAverageLatitudeTest() throws IOException {
        InputStream userOneAddress = parser.PlaceFromText("1615 Riverside Avenue, 47303"); // Ideally these address would be unique
        InputStream userTwoAddress = parser.PlaceFromText("2800 Tillotson Avenue 47304");
        Double latitude = parser.returnAverageLatitude(userOneAddress, userTwoAddress);
        Assertions.assertEquals(40.208118150000004, latitude);
    }
    @Test
    public void ParseUsersAverageLongitudeTest() throws IOException {
        InputStream userOneAddress = parser.PlaceFromText("1615 Riverside Avenue, 47303"); // Ideally these address would be unique
        InputStream userTwoAddress = parser.PlaceFromText("2800 Tillotson Avenue 47304");
        Double longitude = parser.returnAverageLongitude(userOneAddress, userTwoAddress);
        Assertions.assertEquals(-85.41264945, longitude);
    }
    @Test
    public void ParseUserAddressFromURLTest() throws IOException {
        String address = parser.parseVenueAddressURL(40.208118150000004, -85.41264945, "restaurant");
        Assertions.assertEquals("Ball State University North Dining 201, 1525 North McKinley Avenue, Muncie", address);
    }
    @Test
    public void ParseUserAddressURLTest() throws IOException {
        String address = parser.parseUserAddressURL("2720 West Jackson Street Muncie IN 47304");
        Assertions.assertEquals("2720 W Jackson St, Muncie, IN 47303, USA",address);
    }

}
