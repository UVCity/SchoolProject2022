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
    @Test
    public  void parseDistanceURL() throws IOException{
        InputStream directionsPlease = parser.directionsAndDistance("2720 W Jackson St, Muncie, IN 47303, USA", "523 S Tillotson Ave, Muncie, IN 47304, USA");
        String distance = parser.parseDistanceToAddress(directionsPlease);
        Assertions.assertEquals("0.3 mi", distance);
    }
}
