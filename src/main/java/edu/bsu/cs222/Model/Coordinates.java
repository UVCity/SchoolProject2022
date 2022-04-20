package edu.bsu.cs222.Model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;


import java.io.IOException;
import java.io.InputStream;

public class Coordinates {
    private Double lat;
    private Double lng;



    public void parseLatLng(InputStream input) throws IOException {
        JSONArray latLng = JsonPath.read(input, "$..location[?(@.lat)]");
        coordinateSplitter(latLng.get(0).toString());
    }

    public void coordinateSplitter(String coordiantes) {
        String lat = coordiantes.replace("{lat=", "");
        String latitude = (lat.split(",")[0]);
        String lng = coordiantes.replace("}", "");
        String longitude = (lng.split("lng=")[1]);
        setLat(Double.parseDouble(latitude));
        setLng(Double.parseDouble(longitude));
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLat(Double input) {
        this.lat = input;
    }

    public void setLng(Double input) {
        this.lng = input;
    }

    public void coordinatesMidpoint(Coordinates c1, Coordinates c2){
        this.lat = ((c1.getLat() + c2.getLat()) / 2);
        this.lng = ((c1.getLng() + c2.getLng()) / 2);
    }

}
