package edu.bsu.cs222.model;

import edu.bsu.cs222.AddressParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class PlaceDistanceTest {

    private final InputStream placeDirectionsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Directions.json");
    private final AddressParser parser = new AddressParser();

    @Test
    public void parseDistance() throws IOException {
        String distance = parser.parseDistanceToAddress(placeDirectionsStream);
        Assertions.assertEquals("0.3 mi", distance);
    }
}
