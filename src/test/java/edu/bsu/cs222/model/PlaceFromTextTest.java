package edu.bsu.cs222.model;

import edu.bsu.cs222.AddressParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class PlaceFromTextTest {

    public final InputStream placeFromTextStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("FindPlaceFromTextData.json");
    public final AddressParser parser = new AddressParser();

    @Test
    public void parseUserAddressTest() throws IOException {
        String address = parser.parseUserAddress(placeFromTextStream);
        Assertions.assertEquals("2720 W Jackson St, Muncie, IN 47303, USA",address);
    }
    @Test
    public void parseUserLongitude() throws IOException {
        String longitude = parser.parseLongitude(placeFromTextStream);
        Assertions.assertEquals("-85.4186142",longitude);
    }
    @Test
    public void parseUserLatitudeTest() throws IOException {
        String latitude = parser.parseLatitude(placeFromTextStream);
        Assertions.assertEquals("40.1934735", latitude);
    }
    //________________U_R_L___T_E_S_T_I_N_G______________
    @Test
    public void parseUsersAverageLatitudeTest() throws IOException {
        InputStream userOneAddress = parser.PlaceFromText("1615 Riverside Avenue, 47303"); // Ideally these address would be unique
        InputStream userTwoAddress = parser.PlaceFromText("2800 Tillotson Avenue 47304");
        Double latitude = parser.returnAverageLatitude(userOneAddress, userTwoAddress);
        Assertions.assertEquals(40.208118150000004, latitude);
    }
    @Test
    public void parseUsersAverageLongitudeTest() throws IOException {
        InputStream userOneAddress = parser.PlaceFromText("1615 Riverside Avenue, 47303"); // Ideally these address would be unique
        InputStream userTwoAddress = parser.PlaceFromText("2800 Tillotson Avenue 47304");
        Double longitude = parser.returnAverageLongitude(userOneAddress, userTwoAddress);
        Assertions.assertEquals(-85.41264945, longitude);
    }
    @Test
    public void parseUserAddressFromURLTest() throws IOException {
        String address = parser.parseVenueAddressURL(40.208118150000004, -85.41264945, "restaurant");
        Assertions.assertEquals("Ball State University North Dining 201, 1525 North McKinley Avenue, Muncie", address);
    }
    @Test
    public void parseUserAddressURLTest() throws IOException {
        String address = parser.parseUserAddressURL("2720 West Jackson Street Muncie IN 47304");
        Assertions.assertEquals("2720 W Jackson St, Muncie, IN 47303, USA",address);
    }

}
