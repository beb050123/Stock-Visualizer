package com.example.financeplayground;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import com.example.financeplayground.data.DataProcessing;
import javafx.scene.layout.VBox;

import java.text.ParseException;
import java.util.*;

import static com.example.financeplayground.data.DataProcessing.*;

public class HelloController {

  public CategoryAxis graphXAxis;
  public NumberAxis graphYAxis;

  @FXML private ToggleButton day50SMA;
  @FXML private ToggleButton day200SMA;

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
  @FXML XYChart.Series<String, Double> day50SMALine;
  @FXML XYChart.Series<String, Double> day200SMALine;

  @FXML
  public void initialize() {
    stockGraph.setVerticalGridLinesVisible(false);
    stockGraph.setHorizontalGridLinesVisible(false);
    stockGraph.setAnimated(false);
    stockGraph.setLegendVisible(true);
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

    if (day50SMA.isSelected()) {
      fdate = firstDate.getValue().toString();
      sdate = secondDate.getValue().toString();
      stockInfo = DataProcessing.getStockInfo(data, fdate, sdate);
      day50SMALine = new XYChart.Series<>();
      TreeMap<String, Double> get50DaySMA;
      try {
        get50DaySMA = dataProcessing.getSimpleMovingAvg(50, stockInfo);
      } catch (ParseException ex) {
        throw new RuntimeException(ex);
      }
      day50SMALine.setName("50 Day SMA");
      stockGraph.getData().add(day50SMALine);
      for (String key : get50DaySMA.keySet()) {
        day50SMALine.getData().add(new XYChart.Data<>(key, get50DaySMA.get(key)));
      }
      setGraphStyle(day50SMALine);
    } else {
      stockGraph.getData().remove(day50SMALine);
    }
  }

  public void handleGet200SMA(MouseEvent event) throws ParseException {

    if (day200SMA.isSelected()) {
      fdate = firstDate.getValue().toString();
      sdate = secondDate.getValue().toString();
      stockInfo = DataProcessing.getStockInfo(data, fdate, sdate);
      day200SMALine = new XYChart.Series<>();
      TreeMap<String, Double> get200DaySMA;
      try {
        get200DaySMA = dataProcessing.getSimpleMovingAvg(200, stockInfo);
      } catch (ParseException ex) {
        throw new RuntimeException(ex);
      }
      day200SMALine.setName("200 Day SMA");
      stockGraph.getData().add(day200SMALine);
      for (String key : get200DaySMA.keySet()) {
        day200SMALine.getData().add(new XYChart.Data<>(key, get200DaySMA.get(key)));
      }
      setGraphStyle(day200SMALine);
    } else {
      stockGraph.getData().remove(day200SMALine);
    }
  }

  public void handleGetMACD(MouseEvent event) {}

  public void handleGetRSI(MouseEvent event) {}
}
