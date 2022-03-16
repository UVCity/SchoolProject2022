package edu.bsu.cs222.model;

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

    public InputStream PlaceFromText(String address) throws IOException {
        String encodedUrlString = URLEncoder.encode(address, Charset.defaultCharset());

        String urlString = String.format("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=%s&inputtype=textquery&fields=formatted_address%%2Cgeometry&key=AIzaSyBGWTbodP6UEglrxhuu02vh9_VwxL17mfE",encodedUrlString );
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

    private InputStream PlaceNearSearch(Double latitude, Double longitude) throws IOException {
        String format = "%2C";
        String formatter = String.format("%f%s%f",latitude,format, longitude);
        String urlString = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s&rankby=distance&type=restaurant&formatted_address&key=AIzaSyBGWTbodP6UEglrxhuu02vh9_VwxL17mfE",formatter );
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
//______________________________________________________________________________________________________________________
    public Double returnAverageLatitude( InputStream userOneAddress , InputStream userTwoAddress) throws IOException {
        return (Double.parseDouble(parseLatitude(userOneAddress)) + Double.parseDouble(parseLatitude(userTwoAddress)))/2;
    }
    public Double returnAverageLongitude( InputStream userOneAddress , InputStream userTwoAddress) throws IOException {
        return (Double.parseDouble(parseLongitude(userOneAddress)) + Double.parseDouble(parseLongitude(userTwoAddress)))/2;
    }

    public String parseVenueAddress(InputStream testDataStream) throws IOException {
        JSONArray address = JsonPath.read(testDataStream, "$..vicinity");
        return address.get(0).toString();
    }

    public String parseVenueAddressURL(Double latitude, Double longitude) throws IOException {
        JSONArray address = JsonPath.read(PlaceNearSearch(latitude, longitude), "$..vicinity");
        return address.get(0).toString();
    }

    public String parseUserAddress(InputStream testDataStream) throws IOException {
        JSONArray address = JsonPath.read(testDataStream, "$..formatted_address");
        return address.get(0).toString();
    }
    public String parseUserAddressURL(String input) throws IOException{
        JSONArray address = JsonPath.read(PlaceFromText(input), "$..formatted_address");
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

    public String parseLongitude (InputStream testDataStream) throws IOException {
        JSONArray longitude = JsonPath.read(testDataStream, "$..location.lng");
        return longitude.get(0).toString();
    }

    public String parseLatitude (InputStream testDataStream) throws IOException {
        JSONArray latitude = JsonPath.read(testDataStream, "$..location.lat");
        return latitude.get(0).toString();
    }
}
