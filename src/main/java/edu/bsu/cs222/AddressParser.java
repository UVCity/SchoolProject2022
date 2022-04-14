package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.InputStream;
import java.io.IOException;



public class AddressParser {
    //P A R S E R S
    public String parseVenueAddress(InputStream testDataStream) throws IOException {                                    //nearBySearch Api
        JSONArray address = JsonPath.read(testDataStream, "$..vicinity");
        return address.get(0).toString();
    }
    public String parseUserAddress(InputStream testDataStream) throws IOException {                                     //placeFromText Api
        JSONArray address = JsonPath.read(testDataStream, "$..formatted_address");
        return address.get(0).toString();
    }
    public String parseName (InputStream testDataStream) throws IOException {                                           //nearBySearch Api
        JSONArray name = JsonPath.read(testDataStream, "$..name");
        return name.get(0).toString();
    }
    public String parseHoursOfOperation (InputStream testDataStream) throws IOException {                               //nearBySearch Api
        JSONArray openValue = JsonPath.read(testDataStream, "$..open_now");
        return openValue.get(0).toString();
    }
    public String parseDistanceToAddress(InputStream testDataStream) throws IOException {                               //Directions Api
        JSONArray distance = JsonPath.read(testDataStream, "$..distance.text");
        return distance.get(0).toString();
    }
    public Double[] parseLatitudeAndLongitude(InputStream testDataStream) throws IOException {                          // Universal
        JSONArray latitudeAndLongitude = JsonPath.read(testDataStream, "$..location[?(@.lat)]");
        return splitLatitudeAndLongitude(latitudeAndLongitude.get(0).toString());
    }
//________________________________________________________________________________________

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

//_____________________________________________________________________________________________________________________________

}
