package edu.bsu.cs222.Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;


public class URLFormatter {
    InputStream venueUrl;

    APICaller APIcaller = new APICaller();
    KeyReader keyReader = new KeyReader();

    public InputStream placeFromText(String address) throws IOException {
        String encodedUrlString = URLEncoder.encode(address, Charset.defaultCharset());
        String urlString = String.format("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=%s&inputtype=textquery&fields=formatted_address%%2Cgeometry&key=%s",encodedUrlString , keyReader.key());
        return APIcaller.tryCatch(urlString);
    }
    public void placeNearSearch(Double latitude, Double longitude) throws IOException {
        String formatter = String.format("%f%%2C%f",latitude, longitude);
        String urlString = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s&rankby=distance&type=%s&formatted_address&key=%s",formatter, "restaurant", keyReader.key());
        venueUrl = APIcaller.tryCatch(urlString);
    }
    public InputStream directionsAndDistance( String placeFrom , String placeTo) throws IOException {
        String from = URLEncoder.encode(placeFrom, Charset.defaultCharset());
        String to = URLEncoder.encode(placeTo, Charset.defaultCharset());
        String urlString = String.format("https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&key=%s" ,from, to, keyReader.key());
        return APIcaller.tryCatch(urlString);
    }

    public InputStream getVenueUrl() {
        return venueUrl;
    }
}
