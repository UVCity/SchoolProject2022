package edu.bsu.cs222.model;

import edu.bsu.cs222.AddressParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


public class PlaceNearByTest {
    public final InputStream placeNearByStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("PlaceNearBySearchData.json");
    public final AddressParser parser = new AddressParser();
    @Test
    public void ParseVenueAddressTest() throws IOException {
        String address = parser.parseVenueAddress(placeNearByStream);
        Assertions.assertEquals("523 South Tillotson Avenue, Muncie",address);
    }

    @Test
    public void ParseVenueNameTest() throws IOException {
        String name = parser.parseName(placeNearByStream);
        Assertions.assertEquals("Starbucks",name);
    }

    @Test
    public void ParseVenueHoursOfOperationTest() throws IOException {
        String openValue = parser.parseHoursOfOperation(placeNearByStream);
        Assertions.assertEquals("true", openValue);
    }

    @Test
    public void ParseVenueLongitudeTest() throws IOException {
        String longitude = parser.parseLongitude(placeNearByStream);
        Assertions.assertEquals("-85.419575",longitude);
    }
    @Test
    public void ParseVenueLatitudeTest() throws IOException {
        String latitude = parser.parseLatitude(placeNearByStream);
        Assertions.assertEquals("40.189823", latitude);
    }
}
