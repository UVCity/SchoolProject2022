package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class AddressParser {

    public String key() throws IOException {
        InputStream Key = Thread.currentThread().getContextClassLoader().getResourceAsStream("./privateResources/Key.Json");
        JSONArray address = JsonPath.read(Key, "$..key");
        return address.get(0).toString();
    }

    public InputStream placeFromText(String address) throws IOException {
        String encodedUrlString = URLEncoder.encode(address, Charset.defaultCharset());

        String urlString = String.format("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=%s&inputtype=textquery&fields=formatted_address%%2Cgeometry&key=%s",encodedUrlString , key());
        return tryCatch(urlString);
    }
    private InputStream placeNearSearch(Double latitude, Double longitude, String venueType) throws IOException {
        String format = "%2C";
        String formatter = String.format("%f%s%f",latitude,format, longitude);
        String urlString = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s&rankby=distance&type=%s&formatted_address&key=%s",formatter, venueType, key());
        return tryCatch(urlString);
    }
    private InputStream directionsAndDistance( String placeFrom , String placeTo) throws IOException {
        String from = URLEncoder.encode(placeFrom, Charset.defaultCharset());
        String to = URLEncoder.encode(placeTo, Charset.defaultCharset());
        String urlString = String.format("https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&key=%s" ,from, to, key());
        return tryCatch(urlString);
    }
    //______________________________________________________________________________________________________________________
    //returnAverage will need to be modified to run more than two sets of data and return their average
    public Double returnAverage(Double variable1, Double variable2){
        return (variable1 + variable2)/2;
    }
    public String parseVenueAddress(InputStream testDataStream) throws IOException {
        JSONArray address = JsonPath.read(testDataStream, "$..vicinity");
        return address.get(0).toString();
    }
    public String parseVenueAddressURL(Double latitude, Double longitude, String venueType) throws IOException {
        JSONArray address = JsonPath.read(placeNearSearch(latitude, longitude, venueType), "$..vicinity");
        return address.get(0).toString();
    }
    public String parseUserAddress(InputStream testDataStream) throws IOException {
        JSONArray address = JsonPath.read(testDataStream, "$..formatted_address");
        return address.get(0).toString();
    }
    public String parseUserAddressURL(String input) throws IOException{
        JSONArray address = JsonPath.read(placeFromText(input), "$..formatted_address");
        return address.get(0).toString();
    }
    public String parseName (InputStream testDataStream) throws IOException {
        JSONArray name = JsonPath.read(testDataStream, "$..name");
        return name.get(0).toString();
    }
    public String parseHoursOfOperation (InputStream testDataStream) throws IOException {
        JSONArray openValue = JsonPath.read(testDataStream, "$..open_now");
        return openValue.get(0).toString();
    }
    public Double parseLongitude (InputStream testDataStream) throws IOException {
        JSONArray longitude = JsonPath.read(testDataStream, "$..location.lng");
        return (Double) longitude.get(0);
    }
    public Double parseLatitude (InputStream testDataStream) throws IOException {
        JSONArray latitude = JsonPath.read(testDataStream, "$..location.lat");
        return (Double) latitude.get(0);
    }
    public String parseDistanceToAddress(InputStream testDataStream) throws IOException{
        JSONArray distance = JsonPath.read(testDataStream, "$..distance.text");
        return distance.get(0).toString();
    }

    public InputStream tryCatch(String urlString)throws IOException{
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; lyle@uv.city)");
            return connection.getInputStream();
        }
        catch (MalformedURLException malformedURLException){
            throw new RuntimeException(malformedURLException);
        }
    }
}
