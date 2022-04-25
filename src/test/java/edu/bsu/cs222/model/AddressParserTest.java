package edu.bsu.cs222.model;

import edu.bsu.cs222.Model.AddressParser;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;

public class AddressParserTest {
    private final InputStream placeNearByStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("PlaceNearBySearchData.json");
    private final AddressParser parser = new AddressParser();
    {
        try {
            parser.createJsonObject(placeNearByStream);
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void parseVenueAddressTest() throws IOException {
        String address = parser.parseVenueAddress();
        Assertions.assertEquals("523 South Tillotson Avenue, Muncie",address);
    }

    @Test
    public void parseVenueHoursOfOperationTest() throws IOException {
        String openValue = parser.parseHoursOfOperation();
        Assertions.assertEquals("true", openValue);
    }

    @Test
    public void parseVenueNameTest() throws IOException {
        String name = parser.parseName();
        Assertions.assertEquals("Starbucks",name);
    }
}
