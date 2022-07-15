package com.example.financeplayground;

import eu.hansolo.tilesfx.Tile;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.example.financeplayground.data.DataProcessing;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;



public class HelloController {


    @FXML
    private Label welcomeText;
    @FXML
    DataProcessing dataProcessing = new DataProcessing();



     @FXML
     private Tile stockOpen;
    @FXML
     private Tile stockClose;
    @FXML
    private Tile stockChange;
    @FXML
    private DatePicker firstDate;

    @FXML
    private DatePicker secondDate;

    @FXML
    private TextField tickerSelection;

    @FXML
    public void submitButtonAction(MouseEvent event) {


        String fdate = firstDate.getValue().toString();
        String sdate = secondDate.getValue().toString();

        TreeMap<String, ArrayList<String>> stockInfo = dataProcessing.getStockInfo(dataProcessing.getData(), fdate, sdate);

        for (String key : stockInfo.keySet()) {
            System.out.println(key + ": " + stockInfo.get(key));
        }













    }

    @FXML
    public void initialize() {

    }















}