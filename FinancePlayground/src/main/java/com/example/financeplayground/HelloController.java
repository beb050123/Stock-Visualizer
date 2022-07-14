package com.example.financeplayground;

import eu.hansolo.tilesfx.Tile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.example.financeplayground.data.DataProcessing;
import javafx.scene.paint.Color;


public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    DataProcessing dataProcessing = new DataProcessing();


    @FXML
    private TextField dateField;
    @FXML
    private Tile stockOpen;
    @FXML
    private Tile stockClose;
    @FXML
    private Tile stockChange;

    @FXML
    public void submitButtonAction(MouseEvent event) {
        if (dateField.getText().isEmpty()) {
            welcomeText.setText("Please enter a date");
            welcomeText.setStyle("-fx-text-fill: red");

        } else if (!dateField.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
            welcomeText.setText("Invalid date. Please try again.");
            welcomeText.setStyle("-fx-text-fill: red");
        } else {
            welcomeText.setStyle("-fx-text-fill: black");
            welcomeText.setText("Date: " + dateField.getText());
            initialize();
        }
    }

    @FXML
    public void initialize() {
        stockOpen.setValue(dataProcessing.getStockOpen(dateField.getText()));
        stockClose.setValue(dataProcessing.getStockClose(dateField.getText()));
        double stockOpenPrice = stockOpen.getValue();
        stockChange.setValue(((stockClose.getValue() - stockOpen.getValue())/stockOpenPrice)*100);
        if (stockChange.getValue() < 0) {
            stockChange.setTextColor(Color.RED);
        }else{
            stockChange.setTextColor(Color.GREEN);
        }
    }







}