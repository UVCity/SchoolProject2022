package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class APICaller {
    public InputStream tryCatch(String urlString)throws IOException {
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; lyle@uv.city)");
            return connection.getInputStream();
        }
        catch (MalformedURLException malformedURLException){
            throw new RuntimeException(malformedURLException);
        }
    }
}
