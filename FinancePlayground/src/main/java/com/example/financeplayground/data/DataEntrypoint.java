package com.example.financeplayground.data;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;


public class DataEntrypoint {


    public static void main(String[] args) throws FileNotFoundException {


        // print out sorted data
        TreeMap<String, ArrayList<String>> data = DataProcessing.getData();
        TreeMap<String, ArrayList<String>> sortedData = DataProcessing.sortData(data);
        for (String key : sortedData.keySet()) {
            System.out.println(key + ": " + sortedData.get(key));
        }

        DataProcessing.getDates(DataProcessing.getData());
        String date = UserInput.getDate();




    }
}
