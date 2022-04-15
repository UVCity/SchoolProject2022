package edu.bsu.cs222;

public class Coordinates {
    public Double[] coordinatesMidpoint(Double[] coordinatesA, Double[] coordinatesB) {
        Double[] result = new Double[2];
        result[0] = (coordinatesA[0] + coordinatesB[0])/2;
        result[1] = (coordinatesA[1] + coordinatesB[1])/2;
        return result;
    }
    public Double[] coordinateStringSplitter(String locationData) {
        String lat = locationData.replace("{lat=", "");       // delete "{lat="
        String latitude = (lat.split(",")[0]);                // pull string before ","
        String lon = locationData.replace("}", "");           // delete "}"
        String longitude = (lon.split("lng=")[1]);            // pull string after "lng="
        Double[] coordinates = new Double[2];
        coordinates[0] = (Double.parseDouble(latitude));
        coordinates[1] = (Double.parseDouble(longitude));
        return coordinates;
    }
}
