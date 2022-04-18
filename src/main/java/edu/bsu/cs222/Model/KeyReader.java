package edu.bsu.cs222.Model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class KeyReader {
    public String key() throws IOException {
        InputStream Key = Thread.currentThread().getContextClassLoader().getResourceAsStream("./privateResources/Key.Json");
        JSONArray address = JsonPath.read(Key, "$..key");
        return address.get(0).toString();
    }
}
