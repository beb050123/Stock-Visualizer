package com.example.financeplayground.data;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

public class DataProcessing {

  public static TreeMap<String, Double> getData(String ticker) throws IOException, org.json.simple.parser.ParseException {


    TreeMap<String, Double> data = JSONParser.JSONParser(new URL("https://financialmodelingprep.com/api/v3/historical-price-full/" + ticker + "?serietype=line&apikey=5a24bbe1a002b8075a7f10e7e8850e30"));


    return data;
  }

  public static TreeMap<String, Double> getStockInfo(
          TreeMap<String, Double> data, String firstDate, String secondDate) {
    TreeMap<String, Double> stockInfo = new TreeMap<>();
    for (String key : data.keySet()) {
      if (key.compareTo(firstDate) >= 0 && key.compareTo(secondDate) <= 0) {
        stockInfo.put(key, data.get(key));
      }
    }
    return stockInfo;
  }

  public static ArrayList<ArrayList<Date>> getSMA(
      TreeMap<String, Double> data, int period) throws ParseException {

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

  public static TreeMap<String, Double> getSimpleMovingAvg(
      int period, TreeMap<String, Double> data) throws ParseException {

    ArrayList<ArrayList<String>> formattedDates = makeFormattedDates(getSMA(data, period));
    TreeMap<String, Double> smaMap = new TreeMap<>();
    SimpleMovingAverage sma = new SimpleMovingAverage(period);

    for (ArrayList<String> date : formattedDates) {
      TreeMap<String, Double> stockInfo = getStockInfo(data, date.get(0), date.get(1));
      String key1 = stockInfo.keySet().iterator().next();
      for (String key : stockInfo.keySet()) {
        sma.addData(Double.parseDouble(String.valueOf(stockInfo.get(key))));
      }
      smaMap.put(key1, sma.getMean());
    }
    return smaMap;
  }
}
