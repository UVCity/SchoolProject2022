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

    //______________________________________________________________________________P A R S E R S
    public String parseVenueAddress(InputStream testDataStream) throws IOException {
        JSONArray address = JsonPath.read(testDataStream, "$..vicinity");
        return address.get(0).toString();
    }
    public String parseUserAddress(InputStream testDataStream) throws IOException {
        JSONArray address = JsonPath.read(testDataStream, "$..formatted_address");
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
    public String parseDistanceToAddress(InputStream testDataStream) throws IOException {
        JSONArray distance = JsonPath.read(testDataStream, "$..distance.text");
        return distance.get(0).toString();
    }
    public Double[] parseLatitudeAndLongitude(InputStream testDataStream) throws IOException {
        JSONArray latitudeAndLongitude = JsonPath.read(testDataStream, "$..location[?(@.lat)]");
        return splitLatitudeAndLongitude(latitudeAndLongitude.get(0).toString());
    }
//________________________________________________________________________________________
    public InputStream placeFromText(String address) throws IOException {
        String encodedUrlString = URLEncoder.encode(address, Charset.defaultCharset());

        String urlString = String.format("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=%s&inputtype=textquery&fields=formatted_address%%2Cgeometry&key=%s",encodedUrlString , key());
        return tryCatch(urlString);
    }
    public InputStream placeNearSearch(Double latitude, Double longitude, String venueType) throws IOException {
        String format = "%2C";
        String formatter = String.format("%f%s%f",latitude,format, longitude);
        String urlString = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s&rankby=distance&type=%s&formatted_address&key=%s",formatter, venueType, key());
        return tryCatch(urlString);
    }
    public InputStream directionsAndDistance( String placeFrom , String placeTo) throws IOException {
        String from = URLEncoder.encode(placeFrom, Charset.defaultCharset());
        String to = URLEncoder.encode(placeTo, Charset.defaultCharset());
        String urlString = String.format("https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&key=%s" ,from, to, key());
        return tryCatch(urlString);
    }
    public Double[] returnAverage(Double[] coordinatesA, Double[] coordinatesB) {
        Double[] result = new Double[2];
        result[0] = (coordinatesA[0] + coordinatesB[0])/2;
        result[1] = (coordinatesA[1] + coordinatesB[1])/2;
        return result;
    }
    public Double[] splitLatitudeAndLongitude(String locationData) {
        String lat = locationData.replace("{lat=", "");       // delete "{lat="
        String latitude = (lat.split(",")[0]);                // pull string before ","

        String lon = locationData.replace("}", "");           // delete "}"
        String longitude = (lon.split("lng=")[1]);            // pull string after "lng="

        Double[] coordinates = new Double[2];
        coordinates[0] = (Double.parseDouble(latitude));
        coordinates[1] = (Double.parseDouble(longitude));
        return coordinates;
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
//_____________________________________________________________________________________________________________________________
    public String key() throws IOException {
        InputStream Key = Thread.currentThread().getContextClassLoader().getResourceAsStream("./privateResources/Key.Json");
        JSONArray address = JsonPath.read(Key, "$..key");
        return address.get(0).toString();
    }
}
