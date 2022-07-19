package com.example.financeplayground;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import com.example.financeplayground.data.DataProcessing;
import javafx.scene.layout.VBox;

import java.util.*;

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
  @FXML TreeMap<String, ArrayList<String>> data = dataProcessing.getData();

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
  public void initialize(){
    stockGraph.setVerticalGridLinesVisible(false);
    stockGraph.setHorizontalGridLinesVisible(false);
    stockGraph.setAnimated(false);
    stockGraph.setLegendVisible(false);
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
}
