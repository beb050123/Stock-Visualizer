package com.example.financeplayground.data;

import javafx.scene.chart.XYChart;

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

  public static ArrayList<ArrayList<Date>> getSMA(
      TreeMap<String, ArrayList<String>> data, int period) throws ParseException {

    ArrayList<ArrayList<Date>> sma = new ArrayList<>();

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
        Date temp1 = c.getTime();
        c.add(Calendar.DATE, period);
        dates.push(c.getTime());
        Date temp2 = c.getTime();
        sma.add(new ArrayList<>(Arrays.asList(temp1, temp2)));
      } else {
        break;
      }
    }
    return sma;
  }

  public static ArrayList<ArrayList<String>> makeFormattedDates(ArrayList<ArrayList<Date>> dates) {
    ArrayList<ArrayList<String>> formattedsmaDates = new ArrayList<>();
    for (ArrayList<Date> date : dates) {

      StringBuffer sb = new StringBuffer(date.get(0).toString());
      String year = sb.substring(24, 28);
      String month = sb.substring(4, 7);
      String day = sb.substring(8, 10);

      switch (month) {
        case "Jan":
          month = "01";
          break;
        case "Feb":
          month = "02";
          break;
        case "Mar":
          month = "03";
          break;
        case "Apr":
          month = "04";
          break;
        case "May":
          month = "05";
          break;
        case "Jun":
          month = "06";
          break;
        case "Jul":
          month = "07";
          break;
        case "Aug":
          month = "08";
          break;
        case "Sep":
          month = "09";
          break;
        case "Oct":
          month = "10";
          break;
        case "Nov":
          month = "11";
          break;
        case "Dec":
          month = "12";
          break;
      }

      String dateString1 = year + "-" + month + "-" + day;
      StringBuffer sb2 = new StringBuffer(date.get(1).toString());
      String year2 = sb2.substring(24, 28);
      String month2 = sb2.substring(4, 7);
      String day2 = sb2.substring(8, 10);

      switch (month2) {
        case "Jan":
          month2 = "01";
          break;
        case "Feb":
          month2 = "02";
          break;
        case "Mar":
          month2 = "03";
          break;
        case "Apr":
          month2 = "04";
          break;
        case "May":
          month2 = "05";
          break;
        case "Jun":
          month2 = "06";
          break;
        case "Jul":
          month2 = "07";
          break;
        case "Aug":
          month2 = "08";
          break;
        case "Sep":
          month2 = "09";
          break;
        case "Oct":
          month2 = "10";
          break;
        case "Nov":
          month2 = "11";
          break;
        case "Dec":
          month2 = "12";
          break;
      }
      String dateString2 = year2 + "-" + month2 + "-" + day2;
      formattedsmaDates.add(new ArrayList<>(Arrays.asList(dateString1, dateString2)));
    }
    return formattedsmaDates;
  }

  public static TreeMap<String, Double> makeSMAMap(TreeMap<String, ArrayList<String>> data ) throws ParseException {

    ArrayList<ArrayList<String>> formattedDates = makeFormattedDates(getSMA(data, 50));
    ArrayList<Double> prices = new ArrayList<>();
    TreeMap<String, Double> smaMap = new TreeMap<>();

    for (ArrayList<String> date : formattedDates) {
      TreeMap<String, ArrayList<String>> stockInfo = getStockInfo(data, date.get(0), date.get(1));
      String lastKey = stockInfo.keySet().toArray()[stockInfo.keySet().size() - 1].toString();
      double sum = 0;
      for (String key : stockInfo.keySet()) {
        sum += Double.parseDouble(stockInfo.get(key).get(3));

      }
      smaMap.put(lastKey, sum / 50);
    }
    return smaMap;
  }
}
