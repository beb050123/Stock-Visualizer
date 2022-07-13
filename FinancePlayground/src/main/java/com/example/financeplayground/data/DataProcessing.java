package com.example.financeplayground.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


import java.io.InputStream;
import java.util.*;


public class DataProcessing {


    // format the data into a hashmap
    public static HashMap<String, ArrayList<String>> getData() {
        HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
        String url = "https://data.nasdaq.com/api/v3/datasets/WIKI/AAPL.csv?api_key=wcWkYaN6xeyyAS7jCxRC";
        try {
            InputStream is = new java.net.URL(url).openStream();
            Scanner scan = new Scanner(is);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineSplit = line.split(",");
                String key = lineSplit[0];
                ArrayList<String> value = new ArrayList<String>(Arrays.asList(lineSplit).subList(1, lineSplit.length));
                data.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();


        }
        return data;
    }


    public static void getDates(HashMap<String, ArrayList<String>> data) {
        Set<String> keys = data.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
    }


    public static void getStockInfo(HashMap<String, ArrayList<String>> data, String date) {
        String open = "";
        String high = "";
        String low = "";
        String close = "";
        String volume = "";
        for (String key : data.keySet()) {
            if (key.equals(date)) {
                open = data.get(key).get(0);
                high = data.get(key).get(1);
                low = data.get(key).get(2);
                close = data.get(key).get(3);
                volume = data.get(key).get(4);
            }
        }
        System.out.println("Open: " + "$" + open);
        System.out.println("High: " + "$" + high);
        System.out.println("Low: " + "$" + low);
        System.out.println("Close: " + "$" + close);
        System.out.println("Volume: " + "$" + volume);












    }








}


