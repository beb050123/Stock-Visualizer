package com.example.financeplayground.data;

import javafx.scene.chart.PieChart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class DataEntrypoint {

  public static void main(String[] args)
      throws IOException, ParseException, org.json.simple.parser.ParseException {

    String ticker = "META";

    TreeMap<String, Double> data =
        JSONParser.JSONParser(
            new URL(
                "https://financialmodelingprep.com/api/v3/historical-price-full/"
                    + ticker
                    + "?serietype=line&apikey=5a24bbe1a002b8075a7f10e7e8850e30"));
  }
}
