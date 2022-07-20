package com.example.financeplayground.data;

import java.io.InputStream;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

public class DataProcessing {

  public static TreeMap<String, ArrayList<String>> getData() {
    TreeMap<String, ArrayList<String>> data = new TreeMap<>();
    String url =
        "https://data.nasdaq.com/api/v3/datasets/WIKI/AAPL.csv?api_key=wcWkYaN6xeyyAS7jCxRC";
    try {
      InputStream is = new java.net.URL(url).openStream();
      Scanner scan = new Scanner(is);
      while (scan.hasNextLine()) {
        String line = scan.nextLine();
        String[] lineSplit = line.split(",");
        String key = lineSplit[0];
        ArrayList<String> value =
            new ArrayList<>(Arrays.asList(lineSplit).subList(1, lineSplit.length));
        data.put(key, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }

  public static TreeMap<String, ArrayList<String>> getStockInfo(
      TreeMap<String, ArrayList<String>> data, String firstDate, String secondDate) {
    TreeMap<String, ArrayList<String>> stockInfo = new TreeMap<>();
    for (String key : data.keySet()) {
      if (key.compareTo(firstDate) >= 0 && key.compareTo(secondDate) <= 0) {
        stockInfo.put(key, data.get(key));
      }
    }
    return stockInfo;
  }

  public static void getSMA(TreeMap<String, ArrayList<String>> data, int period)
      throws ParseException {

    Stack<Date> dates = new Stack<>();
    String firstDate = data.keySet().iterator().next();

    // TODO - refactor once I get data from Yahoo API
    String secondToLastDate = data.keySet().toArray()[data.keySet().size() - 2].toString();
    Date lastDate = new SimpleDateFormat("yyyy-MM-dd").parse(secondToLastDate);
    dates.push(new SimpleDateFormat("yyyy-MM-dd").parse(firstDate));
    Calendar c = Calendar.getInstance();

    for (String key : data.keySet()) {

      c.setTime(dates.pop());
      if (c.getTime().compareTo(lastDate) < 0) {
        System.out.println("Start Date:" + c.getTime());
        c.add(Calendar.DATE, period);
        dates.push(c.getTime());
        System.out.println("End Date:" + c.getTime());
      } else {
        break;
      }
    }
  }
}
