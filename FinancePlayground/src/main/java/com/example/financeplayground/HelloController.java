package com.example.financeplayground;

import eu.hansolo.tilesfx.Tile;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.example.financeplayground.data.DataProcessing;
import javafx.scene.paint.Color;

import java.util.*;


public class HelloController {


    public CategoryAxis graphXAxis;
    public NumberAxis graphYAxis;
    @FXML
    private Label welcomeText;
    @FXML
    DataProcessing dataProcessing = new DataProcessing();



    @FXML
    private boolean reset = true;
    @FXML
    private DatePicker firstDate;

    @FXML
    private DatePicker secondDate;

    @FXML
    private TextField tickerSelection;
    @FXML
    private LineChart stockGraph;

    @FXML
    public void setReset() {
        stockGraph.getData().clear();

    }

    @FXML
    public void submitButtonAction(MouseEvent event) {


        String fdate = firstDate.getValue().toString();
        String sdate = secondDate.getValue().toString();
        TreeMap<String, ArrayList<String>> stockInfo = dataProcessing.getStockInfo(dataProcessing.getData(), fdate, sdate);
        XYChart.Series<String, Double> series = new XYChart.Series<>();


            series.setName("Stock Price");
            stockGraph.getData().add(series);


            for (String key : stockInfo.keySet()) {
                series.getData().add(new XYChart.Data<>(key, Double.parseDouble(stockInfo.get(key).get(3))));
            }

            setGraphStyle(series);


    }



    @FXML
    public void initialize() {

    }

    @FXML
    public void setGraphStyle(XYChart.Series<String, Double> series) {
        for (XYChart.Data<String, Double> data : series.getData()) {
            data.getNode().setStyle("-fx-shape: circle;");
        }
        graphYAxis.setTickLabelFont(graphYAxis.getTickLabelFont().font("Arial", 10));
    }















}