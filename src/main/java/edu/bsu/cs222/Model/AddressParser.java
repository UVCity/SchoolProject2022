package edu.bsu.cs222.Model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.InputStream;
import java.io.IOException;

public class AddressParser {

    private JSONObject jo;

    public String parseVenueAddress() throws IOException {                                    //nearBySearch Api
        JSONArray address = JsonPath.read(jo, "$..vicinity");
        return address.get(0).toString();
    }
    public String parseName() throws IOException {                                           //nearBySearch Api
        JSONArray name = JsonPath.read(jo, "$..name");
        return name.get(0).toString();
    }
    public String parseHoursOfOperation() throws IOException {                               //nearBySearch Api
        JSONArray openValue = JsonPath.read(jo, "$..open_now");
        return openValue.get(0).toString();
    }

    public void createJsonObject(InputStream venueUrl) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        this.jo = (JSONObject)jsonParser.parse(venueUrl);
    }



}
