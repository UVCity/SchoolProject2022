package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.InputStream;
import java.io.IOException;



public class AddressParser {
    public String parseVenueAddress(InputStream testDataStream) throws IOException {                                    //nearBySearch Api
        JSONArray address = JsonPath.read(testDataStream, "$..vicinity");
        return address.get(0).toString();
    }
    public String parseUserAddress(InputStream testDataStream) throws IOException {                                     //placeFromText Api
        JSONArray address = JsonPath.read(testDataStream, "$..formatted_address");
        return address.get(0).toString();
    }
    public String parseName(InputStream testDataStream) throws IOException {                                           //nearBySearch Api
        JSONArray name = JsonPath.read(testDataStream, "$..name");
        return name.get(0).toString();
    }
    public String parseHoursOfOperation(InputStream testDataStream) throws IOException {                               //nearBySearch Api
        JSONArray openValue = JsonPath.read(testDataStream, "$..open_now");
        return openValue.get(0).toString();
    }
    public String parseDistanceToAddress(InputStream testDataStream) throws IOException {                               //Directions Api
        JSONArray distance = JsonPath.read(testDataStream, "$..distance.text");
        return distance.get(0).toString();
    }
    public Double[] parseLatitudeAndLongitude(InputStream testDataStream) throws IOException {                          // Universal
        JSONArray latitudeAndLongitude = JsonPath.read(testDataStream, "$..location[?(@.lat)]");
        Coordinates locationData = new Coordinates();
        return locationData.coordinateStringSplitter(latitudeAndLongitude.get(0).toString());
    }
}
