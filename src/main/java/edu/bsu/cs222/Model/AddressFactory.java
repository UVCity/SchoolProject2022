package edu.bsu.cs222.Model;

import java.io.IOException;
import java.io.InputStream;


public class AddressFactory {
    private InputStream addressUrl;

    private final URLFormatter urlFormatter = new URLFormatter();
    private final Coordinates coordinates = new Coordinates();

    public InputStream getUrl(){
        return addressUrl;
    }

    public void setUrl(InputStream input) {
        this.addressUrl = input;
    }

    public void formatUserInput(String userAddress) {
        try {
            setUrl(urlFormatter.placeFromText(userAddress));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseCoordinates(InputStream addressUrl) {
        try {
            coordinates.parseLatLng(addressUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Coordinates getCoordinates () {
        return this.coordinates;
    }

}
