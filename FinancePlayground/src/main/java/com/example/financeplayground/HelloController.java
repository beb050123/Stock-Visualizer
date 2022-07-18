package com.example.financeplayground;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.example.financeplayground.data.DataProcessing;


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
    String fdate;
    String sdate;
    TreeMap<String, ArrayList<String>> stockInfo;
    XYChart.Series<String, Double> series;

    @FXML
    public void submitButtonAction(MouseEvent event) {

        fdate = firstDate.getValue().toString();
        sdate = secondDate.getValue().toString();
        stockInfo = dataProcessing.getStockInfo(dataProcessing.getData(), fdate, sdate);
        series = new XYChart.Series<>();


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

    //TODO - add mouseover to graph to display stock info

    @FXML
    public void onMouseOver(MouseEvent event) {

        Node chartBackground = stockGraph.lookup(".chart-plot-background");

        chartBackground.setOnMouseEntered(e -> {
            System.out.println("Mouse at : " + graphXAxis.getValueForDisplay(e.getX()) + " " +
                    graphYAxis.getValueForDisplay(e.getY()));
        });


    }






}























