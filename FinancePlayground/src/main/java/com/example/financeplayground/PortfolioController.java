package com.example.financeplayground;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import com.example.financeplayground.data.DataProcessing;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static com.example.financeplayground.data.DataProcessing.*;

public class PortfolioController {

    public Button displayPortfolio;
    public Button displayNews;
    public Button displayWatchlist;
    public Button displayCharts;
    public VBox vbox1;


    @FXML
    public void initialize() {
    }

    public void handleMenuClick(MouseEvent e){
        if(e.getTarget().toString().contains("Portfolio")){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("portfolio.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 1600,900);
                scene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("stylesheet.css")).toExternalForm());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {

            }
        }
        if(e.getTarget().toString().contains("Charts")){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("charts.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 1600,900);
                scene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("stylesheet.css")).toExternalForm());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {

            }
        }
        if(e.getTarget().toString().contains("News")){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("news.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 1600,900);
                scene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("stylesheet.css")).toExternalForm());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {

            }
        }
        if(e.getTarget().toString().contains("Watchlist")){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("watchlist.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 1600,900);
                scene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("stylesheet.css")).toExternalForm());
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {

            }
        }


    }




}
