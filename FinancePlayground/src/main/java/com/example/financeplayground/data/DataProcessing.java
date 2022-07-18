package com.example.financeplayground.data;


import java.io.InputStream;
import java.util.*;


public class DataProcessing {



    public static TreeMap<String, ArrayList<String>> getData() {
        TreeMap<String, ArrayList<String>> data = new TreeMap<>();
        String url = "https://data.nasdaq.com/api/v3/datasets/WIKI/AAPL.csv?api_key=wcWkYaN6xeyyAS7jCxRC";
        try {
            InputStream is = new java.net.URL(url).openStream();
            Scanner scan = new Scanner(is);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineSplit = line.split(",");
                String key = lineSplit[0];
                ArrayList<String> value = new ArrayList<>(Arrays.asList(lineSplit).subList(1, lineSplit.length));
                data.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();


        }
        return data;
    }



    public static Set<String> getDates(TreeMap<String, ArrayList<String>> data) {
        Set<String> dates = new HashSet<>(data.keySet());
        return dates;
    }



    public static TreeMap<String, ArrayList<String>> sortData(TreeMap<String, ArrayList<String>> data) {
        TreeMap<String, ArrayList<String>> sortedData = new TreeMap<>();
        for (String key : data.keySet()) {
            sortedData.put(key, data.get(key));
        }
        return sortedData;
    }


    public static TreeMap<String, ArrayList<String>> getStockInfo(TreeMap<String, ArrayList<String>> data, String firstDate, String secondDate) {
        TreeMap<String, ArrayList<String>> stockInfo = new TreeMap<>();
        for (String key : data.keySet()) {
            if (key.compareTo(firstDate) >= 0 && key.compareTo(secondDate) <= 0) {
                stockInfo.put(key, data.get(key));
            }
        }
        return stockInfo;
    }









}


