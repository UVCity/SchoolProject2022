package edu.bsu.cs222.Model;

import com.jayway.jsonpath.JsonPath;


import java.io.IOException;
import java.io.InputStream;

public class Coordinates {
    private Double lat;
    private Double lng;


    public void parseLatitude(InputStream testDataStream) throws IOException {
        this.lat = JsonPath.read(testDataStream, "$..location..lat");

    }

    public void parseLongitude(InputStream testDataStream) throws IOException {
        this.lng = JsonPath.read(testDataStream, "$..location..lng");
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public void coordinatesMidpoint(Coordinates c1, Coordinates c2){
        this.lat = ((c1.getLat() + c2.getLat()) / 2);
        this.lng = ((c1.getLng() + c2.getLng()) / 2);
    }

    public Double[] coordinatesMidpoint(Double[] coordinatesA, Double[] coordinatesB) {
        Double[] result = new Double[2];
        result[0] = (coordinatesA[0] + coordinatesB[0])/2;
        result[1] = (coordinatesA[1] + coordinatesB[1])/2;
        return result;
    }

}
