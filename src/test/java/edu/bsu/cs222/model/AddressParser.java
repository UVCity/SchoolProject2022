package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.InputStream;
import java.io.IOException;

public class AddressParser {

    public String parseAddress (InputStream testDataStream) throws IOException {
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

    public String parseLongitude (InputStream testDataStream) throws IOException {
        JSONArray longitude = JsonPath.read(testDataStream, "$..location.lng");
        return longitude.get(0).toString();
    }

    public String parseLatitude (InputStream testDataStream) throws IOException {
        JSONArray latitude = JsonPath.read(testDataStream, "$..location.lat");
        return latitude.get(0).toString();
    }
}
