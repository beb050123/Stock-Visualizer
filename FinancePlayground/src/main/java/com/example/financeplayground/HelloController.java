package com.example.financeplayground;

import eu.hansolo.tilesfx.Tile;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
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
        if (tickerSelection.getText().isEmpty()) {


        } else if (!tickerSelection.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {

        } else {

            initialize();
        }
    }

    @FXML
    public void initialize() {

    }











}