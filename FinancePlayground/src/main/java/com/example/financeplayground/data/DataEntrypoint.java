package com.example.financeplayground.data;


import java.io.FileNotFoundException;


public class DataEntrypoint {


    public static void main(String[] args) throws FileNotFoundException {




        DataProcessing.getDates(DataProcessing.getData());
        String date = UserInput.getDate();
        DataProcessing.getStockInfo(DataProcessing.getData(), date);



    }
}
