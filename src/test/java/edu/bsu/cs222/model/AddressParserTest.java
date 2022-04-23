package edu.bsu.cs222.model;

import edu.bsu.cs222.Model.AddressParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class AddressParserTest {
    private final InputStream placeNearByStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("PlaceNearBySearchData.json");
    private final AddressParser parser = new AddressParser();


    @Test
    public void parseVenueAddressTest() throws IOException {
        String address = parser.parseVenueAddress(placeNearByStream);
        Assertions.assertEquals("523 South Tillotson Avenue, Muncie",address);
    }
    @Test
    public void parseVenueHoursOfOperationTest() throws IOException {
        String openValue = parser.parseHoursOfOperation(placeNearByStream);
        Assertions.assertEquals("true", openValue);
    }
    @Test
    public void parseVenueNameTest() throws IOException {
        String name = parser.parseName(placeNearByStream);
        Assertions.assertEquals("Starbucks",name);
    }
}
