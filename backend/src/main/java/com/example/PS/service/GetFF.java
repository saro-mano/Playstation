package com.example.PS.service;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetFF {

    public String convert(int input){
        String result = Integer.toBinaryString(input);
        System.out.println(result);
        while(result.length() < 5){
            result = "0" + result;
        }
        return result;
    }

    public String run() throws IOException, JSONException {
        URL url = new URL("http://localhost:12300/featureflags");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        //Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }

        JSONArray obj = new JSONArray(inline);
        int value = 0;
        for(int i = 0; i < obj.length() ; i++){
            if(obj.getJSONObject(i).getString("name").equals("Identity_Information")){
                value = obj.getJSONObject(i).getInt("value");
            };
        }
        String result = convert(value);

        return result;
    }
}
