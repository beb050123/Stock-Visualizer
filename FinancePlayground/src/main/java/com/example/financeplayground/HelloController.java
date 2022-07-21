package com.example.financeplayground;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import com.example.financeplayground.data.DataProcessing;
import javafx.scene.layout.VBox;

import java.text.ParseException;
import java.util.*;

import static com.example.financeplayground.data.DataProcessing.*;

public class HelloController {

  public CategoryAxis graphXAxis;
  public NumberAxis graphYAxis;

  @FXML private Label dateLabel;
  @FXML private Label priceLabel;
  @FXML private VBox infoPopUp;

  @FXML private final boolean reset = true;
  @FXML private DatePicker firstDate;

  @FXML private DatePicker secondDate;

  @FXML DataProcessing dataProcessing = new DataProcessing();
  @FXML TreeMap<String, ArrayList<String>> data = getData();


  @FXML private LineChart stockGraph;

  @FXML
  public void setReset() {
    stockGraph.getData().clear();
  }

  @FXML Node chartBackground;

  @FXML String fdate;
  String sdate;
  TreeMap<String, ArrayList<String>> stockInfo;
  XYChart.Series<String, Double> series;

  @FXML
  public void initialize() {
    stockGraph.setVerticalGridLinesVisible(false);
    stockGraph.setHorizontalGridLinesVisible(false);
    stockGraph.setAnimated(false);
    stockGraph.setLegendVisible(false);
    graphXAxis.setTickMarkVisible(false);
  }

  @FXML
  public void submitButtonAction(MouseEvent event) {

    fdate = firstDate.getValue().toString();
    sdate = secondDate.getValue().toString();
    stockInfo = DataProcessing.getStockInfo(data, fdate, sdate);
    series = new XYChart.Series<>();
    series.setName("Stock Price");
    stockGraph.getData().add(series);
    chartBackground = stockGraph.lookup(".chart-plot-background");

    for (String key : stockInfo.keySet()) {
      series.getData().add(new XYChart.Data<>(key, Double.parseDouble(stockInfo.get(key).get(3))));
    }

    setGraphStyle(series);
  }

  @FXML
  public void setGraphStyle(XYChart.Series<String, Double> series) {
    for (XYChart.Data<String, Double> data : series.getData()) {
      data.getNode().setStyle("-fx-shape: \"\";");
      data.getNode().setStyle("-fx-padding: 0px;");
      data.getNode().setStyle("-fx-background-radius: 0px;");
      data.getNode().setStyle("-fx-background-insets: 0,0;");
      data.getNode().setStyle("-fx-background-color: transparent;");
    }
  }

  @FXML
  public void onMouseOver(MouseEvent event) {

    chartBackground.setOnMouseMoved(
        e -> {
          dateLabel.setText("Date: " + graphXAxis.getValueForDisplay(e.getX()));
          for (XYChart.Data<String, Double> data : series.getData()) {
            if (data.getXValue().equals(graphXAxis.getValueForDisplay(e.getX()))) {
              priceLabel.setText("Price: " + data.getYValue());
              infoPopUp.setLayoutX(data.getNode().getLayoutX() + 10);
              infoPopUp.setLayoutY(data.getNode().getLayoutY() - infoPopUp.getHeight());
            }
          }
          infoPopUp.setVisible(true);
        });
  }

  public void handleGet50SMA(MouseEvent event) throws ParseException {

    fdate = firstDate.getValue().toString();
    sdate = secondDate.getValue().toString();
    stockInfo = DataProcessing.getStockInfo(data, fdate, sdate);
    XYChart.Series<String, Double> series = new XYChart.Series<>();
    TreeMap<String, Double> get50DaySMA = dataProcessing.getSimpleMovingAvg(50,stockInfo);

    series.setName("50 Day SMA");
    stockGraph.getData().add(series);
    for (String key : get50DaySMA.keySet()) {
      series.getData().add(new XYChart.Data<>(key, get50DaySMA.get(key)));
    }
    System.out.println(get50DaySMA);
    setGraphStyle(series);

  }

  public void handleGetEMA(MouseEvent event) {}

  public void handleGetMACD(MouseEvent event) {}

  public void handleGetRSI(MouseEvent event) {}
}
