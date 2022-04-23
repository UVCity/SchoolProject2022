package edu.bsu.cs222.Model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.InputStream;
import java.io.IOException;

public class AddressParser {

    public String parseVenueAddress(InputStream venueUrl) throws IOException {                                    //nearBySearch Api
        JSONArray address = JsonPath.read(venueUrl, "$..vicinity");
        return address.get(0).toString();
    }
    public String parseName(InputStream venueUrl) throws IOException {                                           //nearBySearch Api
        JSONArray name = JsonPath.read(venueUrl, "$..name");
        return name.get(0).toString();
    }
    public String parseHoursOfOperation(InputStream venueUrl) throws IOException {                               //nearBySearch Api
        JSONArray openValue = JsonPath.read(venueUrl, "$..open_now");
        return openValue.get(0).toString();
    }


}
