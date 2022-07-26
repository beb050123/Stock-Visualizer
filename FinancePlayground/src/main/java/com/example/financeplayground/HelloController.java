package com.example.financeplayground;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import com.example.financeplayground.data.DataProcessing;
import javafx.scene.layout.VBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static com.example.financeplayground.data.DataProcessing.*;

public class HelloController {

  public CategoryAxis graphXAxis;
  public NumberAxis graphYAxis;

  @FXML private MFXToggleButton day50SMA;

  @FXML private MFXToggleButton day12EMA;
  @FXML private MFXToggleButton day26EMA;
  @FXML private MFXToggleButton day200SMA;

  @FXML private Label dateLabel;
  @FXML private Label priceLabel;
  @FXML private VBox infoPopUp;
  @FXML private MFXDatePicker firstDate;
  @FXML private String stockTicker;
  @FXML private MFXDatePicker secondDate;
  @FXML private MFXTextField tickerSelection;

  @FXML DataProcessing dataProcessing = new DataProcessing();
  @FXML TreeMap<String, Double> data;

  @FXML private LineChart stockGraph;

  @FXML
  public void setReset() {
    stockGraph.getData().clear();
  }

  @FXML Node chartBackground;

  @FXML String fdate;
  String sdate;
  TreeMap<String, Double> stockInfo;
  XYChart.Series<String, Double> series;
  @FXML XYChart.Series<String, Double> day50SMALine;
  @FXML XYChart.Series<String, Double> day200SMALine;
  @FXML private XYChart.Series<String, Double> day12EMALine;
  @FXML private XYChart.Series<String, Double> day26EMALine;

  @FXML
  public void initialize() {
    stockGraph.setVerticalGridLinesVisible(false);
    stockGraph.setHorizontalGridLinesVisible(false);
    stockGraph.setAnimated(false);
    stockGraph.setLegendVisible(true);
    graphXAxis.setTickMarkVisible(false);
  }

  @FXML
  public void submitButtonAction(MouseEvent event)
      throws IOException, org.json.simple.parser.ParseException {

    stockTicker = tickerSelection.getText();
    stockGraph.setTitle(stockTicker);
    if (stockTicker.length() < 1 || stockTicker.matches("\\d*") || stockTicker.length() > 5) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("No data found");
      alert.setContentText("Enter a valid ticker symbol");
      alert.showAndWait();
      return;
    }
    try {
      data = getData(stockTicker);
    } catch (RuntimeException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("No data found");
      alert.setContentText("Enter a valid ticker symbol");
      alert.showAndWait();
      return;
    }

    try {
      fdate = firstDate.getValue().toString();
      sdate = secondDate.getValue().toString();
    } catch (NullPointerException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("No data found");
      alert.setContentText("Enter a valid date range");
      alert.showAndWait();
      return;
    }

    if (fdate.length() < 1 || fdate.length() > 10) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("No data found");
      alert.setContentText("Enter a first valid date");
      alert.showAndWait();
      return;
    }
    if (sdate.length() < 1 || sdate.length() > 10) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("No data found");
      alert.setContentText("Enter a second valid date");
      alert.showAndWait();
      return;
    }
    stockInfo = DataProcessing.getStockInfo(data, fdate, sdate);
    series = new XYChart.Series<>();
    series.setName(stockTicker);
    stockGraph.getData().add(series);
    chartBackground = stockGraph.lookup(".chart-plot-background");

    for (String key : stockInfo.keySet()) {
      series
          .getData()
          .add(new XYChart.Data<>(key, Double.parseDouble(stockInfo.get(key).toString())));
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

    try {
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
    } catch (Exception e) {

    }
  }

  public void handleGet50SMA(MouseEvent event) throws ParseException {

    try {
      if (day50SMA.isSelected()) {
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
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("No data found");
      alert.setContentText("Please select a date range and ticker symbol");
      alert.showAndWait();
    }
  }

  public void handleGet200SMA(MouseEvent event) throws ParseException {

    try {
      if (day200SMA.isSelected()) {
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
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("No data found");
      alert.setContentText("Please select a date range and ticker symbol");
      alert.showAndWait();
    }
  }

  public void handleGet12EMA(MouseEvent event) throws ParseException {

    try {
      if (day12EMA.isSelected()) {
        day12EMALine = new XYChart.Series<>();
        TreeMap<String, Double> get12DayEMA;
        try {
          get12DayEMA = dataProcessing.getExponentialMovingAvg(12, stockInfo);
        } catch (ParseException ex) {
          throw new RuntimeException(ex);
        }
        day12EMALine.setName("12 Day EMA");
        stockGraph.getData().add(day12EMALine);
        for (String key : get12DayEMA.keySet()) {
          day12EMALine.getData().add(new XYChart.Data<>(key, get12DayEMA.get(key)));
        }
        setGraphStyle(day12EMALine);
      } else {
        stockGraph.getData().remove(day12EMALine);
      }
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("No data found");
      alert.setContentText("Please select a date range and ticker symbol");
      alert.showAndWait();
    }
  }

  public void handleGet26EMA(MouseEvent event) throws ParseException {

    try {
      if (day26EMA.isSelected()) {
        day26EMALine = new XYChart.Series<>();
        TreeMap<String, Double> get26DayEMA;
        try {
          get26DayEMA = dataProcessing.getExponentialMovingAvg(26, stockInfo);
        } catch (ParseException ex) {
          throw new RuntimeException(ex);
        }
        day26EMALine.setName("26 Day EMA");
        stockGraph.getData().add(day26EMALine);
        for (String key : get26DayEMA.keySet()) {
          day26EMALine.getData().add(new XYChart.Data<>(key, get26DayEMA.get(key)));
        }
        setGraphStyle(day26EMALine);
      } else {
        stockGraph.getData().remove(day26EMALine);
      }
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("No data found");
      alert.setContentText("Please select a date range and ticker symbol");
      alert.showAndWait();
    }
  }

  public void handleGetMACD(MouseEvent event) {}

  public void handleGetRSI(MouseEvent event) {}
}
