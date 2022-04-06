package edu.bsu.cs222.model;

import edu.bsu.cs222.AddressParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class PlaceNearByTest {
    private final InputStream placeNearByStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("PlaceNearBySearchData.json");
    private final AddressParser parser = new AddressParser();
    @Test
    public void parseVenueAddressTest() throws IOException {
        String address = parser.parseVenueAddress(placeNearByStream);
        Assertions.assertEquals("523 South Tillotson Avenue, Muncie",address);
    }
    @Test
    public void parseVenueNameTest() throws IOException {
        String name = parser.parseName(placeNearByStream);
        Assertions.assertEquals("Starbucks",name);
    }
    @Test
    public void parseVenueHoursOfOperationTest() throws IOException {
        String openValue = parser.parseHoursOfOperation(placeNearByStream);
        Assertions.assertEquals("true", openValue);
    }
    @Test
    public void parseVenueLatitudeAndLongitudeTest() throws IOException {
        Double[] latitudeAndLongitude = parser.parseLatitudeAndLongitude(placeNearByStream);
        Assertions.assertEquals(40.189823, latitudeAndLongitude[0]);
        Assertions.assertEquals(-85.419575,latitudeAndLongitude[1]);
    }
    //________________U_R_L___T_E_S_T_I_N_G______________
    @Test
    public void parseUsersAverageLatitudeAndLongitudeFromURLTest() throws IOException {
        InputStream userOneAddress = parser.placeFromText("1615 Riverside Avenue, 47303");
        InputStream userTwoAddress = parser.placeFromText("2800 Tillotson Avenue 47304");
        Double[] coordinatesA = parser.parseLatitudeAndLongitude(userOneAddress);
        Double[] coordinatesB = parser.parseLatitudeAndLongitude(userTwoAddress);
        Double[] coordinates = parser.returnAverage(coordinatesA, coordinatesB);

        Assertions.assertEquals(40.208118150000004, coordinates[0]);
        Assertions.assertEquals(-85.41264945, coordinates[1]);
    }
    @Test
    public void parseVenueNearByFromURLTest() throws IOException {
        InputStream place1 = parser.placeFromText("1615 Riverside Avenue 47303, Muncie IN");
        InputStream place2 = parser.placeFromText("2800 Tillotson Avenue 47304, Muncie IN ");
        Double[] coordinatesA = parser.parseLatitudeAndLongitude(place1);
        Double[] coordinatesB = parser.parseLatitudeAndLongitude(place2);
        Double[] midPointLocation = parser.returnAverage(coordinatesA, coordinatesB);

        InputStream venue = parser.placeNearSearch(midPointLocation[0],midPointLocation[1],"restaurant");
        String address = parser.parseVenueAddress(venue);
        Assertions.assertEquals("Ball State University North Dining 201, 1525 North McKinley Avenue, Muncie", address);
    }
}