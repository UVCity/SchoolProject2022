package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LetsLynkApplicationTest {

    @Test
    public void addressFormatTest() throws IOException {
        UserInputReader userInput = new UserInputReader();
        String address =  userInput.formatAddress("123 Elm St.", "47306", "IN", "Muncie");
        Assertions.assertEquals("123 Elm St. Muncie, IN 47306",address);
    }
}
