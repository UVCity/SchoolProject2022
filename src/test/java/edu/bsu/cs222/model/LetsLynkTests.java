package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class LetsLynkTests {

    public final InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("PlaceNearBySearchData.json");
    public final AddressParser parser = new AddressParser();


    @Test
    public void ParseAddressTest() throws IOException {
        String address = parser.parseAddress(testDataStream);
        Assertions.assertEquals("2000 W University Ave, Muncie, IN 47306, United States",address);
    }

    @Test
    public void ParseNameTest() throws IOException {
        String name = parser.parseName(testDataStream);
        Assertions.assertEquals("Starbucks",name);
    }

    @Test
    public void ParseHoursOfOperationTest() throws IOException {
        String openValue = parser.parseHoursOfOperation(testDataStream);
        Assertions.assertEquals("false", openValue);
    }

    @Test
    public void ParseLongitudeTest() throws IOException {
        String longitude = parser.parseLongitude(testDataStream);
        Assertions.assertEquals("-85.419575",longitude);
    }

    @Test
    public void ParseLatitudeTest() throws IOException {
        String latitude = parser.parseLatitude(testDataStream);
        Assertions.assertEquals("40.197237", latitude);
    }
}
