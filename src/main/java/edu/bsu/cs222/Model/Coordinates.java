package edu.bsu.cs222.Model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;


import java.io.IOException;
import java.io.InputStream;

public class Coordinates {
    private Double latitude;
    private Double longitude;



    public void parseLatLng(InputStream input) throws IOException {
        JSONArray latLng = JsonPath.read(input, "$..location[?(@.lat)]");
        coordinateSplitter(latLng.get(0).toString());
    }

    public void coordinateSplitter(String coordiantes) {
        String lat = coordiantes.replace("{lat=", "");
        String latitude = (lat.split(",")[0]);
        String lng = coordiantes.replace("}", "");
        String longitude = (lng.split("lng=")[1]);
        setLatitude(Double.parseDouble(latitude));
        setLongitude(Double.parseDouble(longitude));
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLatitude(Double input) {
        this.latitude = input;
    }

    public void setLongitude(Double input) {
        this.longitude = input;
    }

    public void findCoordinatesMidpoint(Coordinates coordinate1, Coordinates coordinate2){
        this.latitude = ((coordinate1.getLatitude() + coordinate2.getLatitude()) / 2);
        this.longitude = ((coordinate1.getLongitude() + coordinate2.getLongitude()) / 2);
    }

}
