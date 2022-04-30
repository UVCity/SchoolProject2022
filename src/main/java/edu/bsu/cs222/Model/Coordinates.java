package edu.bsu.cs222.Model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Double.parseDouble;

public class Coordinates {
    public Double latitude;
    public Double longitude;

    public void parseLatLng(InputStream input) throws IOException {
        JSONArray latLng = JsonPath.read(input, "$..location[?(@.lat)]");
        splitCoordinates(latLng.get(0).toString());
    }

    private void splitCoordinates(String coordinates) {
        String lat = coordinates.replace("{lat=", "");
        String latitude = (lat.split(",")[0]);
        String lng = coordinates.replace("}", "");
        String longitude = (lng.split("lng=")[1]);
        this.latitude = parseDouble(latitude);
        this.longitude = parseDouble(longitude);
    }

    public void findCoordinatesMidpoint(Coordinates coordinate1, Coordinates coordinate2){
        this.latitude = ((coordinate1.latitude + coordinate2.latitude) / 2);
        this.longitude = ((coordinate1.longitude + coordinate2.longitude) / 2);
    }
}
