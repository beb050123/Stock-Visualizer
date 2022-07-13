package com.example.financeplayground;

import eu.hansolo.tilesfx.Tile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class HelloController {
    @FXML
    private Label welcomeText;


    @FXML
    private TextField dateField;
    @FXML
    private Tile stockOpen;
    @FXML
    private Tile stockLow;
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
        }
    }

    @FXML
    public double initialize() {
        stockOpen.setTitle("Open");
        stockOpen.setValue(100.0);
        return 0.0;
    }








}