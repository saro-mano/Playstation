package com.example.PS.service;

import org.json.JSONException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class UpdateFF {
    public void update(String num) throws IOException, JSONException {
        URL url = new URL("http://localhost:12300/featureflags");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);

        String s = "{\"name\":\"Identity_Information\",\"value\":" + Integer.parseInt(num) + "}";

        byte[] out =  s.getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

        int statusCode = http.getResponseCode();
        System.out.println(statusCode);
    }
}
