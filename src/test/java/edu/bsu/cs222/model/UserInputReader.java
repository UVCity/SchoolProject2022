package edu.bsu.cs222.model;

import java.io.IOException;
import java.io.InputStream;

public class UserInputReader {
    public String formatAddress(String streetAddress , String zipcode, String state, String city) {
        String formattedAddress;
        formattedAddress = streetAddress + " " + city + ", " + state + " " + zipcode;
        return formattedAddress;
    }
}
