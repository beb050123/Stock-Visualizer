package com.example.financeplayground.data;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.json.simple.*;
import org.json.simple.parser.ParseException;


public class JSONParser {


  public static TreeMap<String, Double> JSONParser(URL url) throws IOException, ParseException {

    TreeMap<String, Double> data = new TreeMap<>();
    org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
    jsonParser.parse(new InputStreamReader(url.openStream()));
    JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(url.openStream()));
    JSONArray jsonArray = (JSONArray) jsonObject.get("historical");
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
      data.put(jsonObject2.get("date").toString(), (Double) jsonObject2.get("close"));
    }
    return data;
  }


}
