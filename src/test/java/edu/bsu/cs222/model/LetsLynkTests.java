package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class LetsLynkTests {

    public final InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
    public final UserInputParser parser = new UserInputParser();


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

}
