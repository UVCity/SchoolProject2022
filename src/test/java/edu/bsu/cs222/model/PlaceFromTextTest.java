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
        Double longitude = parser.parseLongitude(placeFromTextStream);
        Assertions.assertEquals(-85.4186142,longitude);
    }
    @Test
    public void parseUserLatitudeTest() throws IOException {
        Double latitude = parser.parseLatitude(placeFromTextStream);
        Assertions.assertEquals(40.1934735, latitude);
    }
    //________________U_R_L___T_E_S_T_I_N_G______________
    @Test
    public void parseUsersAverageLatitudeTest() throws IOException {
        InputStream userOneAddress = parser.placeFromText("1615 Riverside Avenue, 47303"); // Ideally these address would be unique
        Double latitude1 = parser.parseLatitude(userOneAddress);
        InputStream userTwoAddress = parser.placeFromText("2800 Tillotson Avenue 47304");
        Double latitude2 = parser.parseLatitude(userTwoAddress);
        Double avgLat = parser.returnAverage(latitude1, latitude2);
        Assertions.assertEquals(40.208118150000004, avgLat);
    }
    @Test
    public void parseUsersAverageLongitudeTest() throws IOException {
        InputStream userOneAddress = parser.placeFromText("1615 Riverside Avenue, 47303"); // Ideally these address would be unique
        Double longitude1 = parser.parseLongitude(userOneAddress);
        InputStream userTwoAddress = parser.placeFromText("2800 Tillotson Avenue 47304");
        Double longitude2 = parser.parseLongitude(userTwoAddress);
        Double avgLong = parser.returnAverage(longitude1, longitude2);
        Assertions.assertEquals(-85.41264945, avgLong);
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
