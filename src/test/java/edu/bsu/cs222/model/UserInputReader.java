package edu.bsu.cs222.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class UserInputReader {
    public String formatAddress() {
        String formattedAddress;
        formattedAddress = getStreetAddress() + " " + getCity() + ", " + getState() + " " + getZipcode();
        return formattedAddress;
    }

    //TODO once parse is available, make these parse for information

    private String getCity(){
        return "Muncie";
    }

    private String getState(){
        return "IN";
    }

    private String getZipcode(){
        return "47306";
    }

    private String getStreetAddress(){
        return "123 Elm St.";
    }

}
