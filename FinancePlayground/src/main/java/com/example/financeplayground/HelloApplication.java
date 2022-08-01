package com.example.financeplayground;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {




    @Override
    public void start(Stage stage) throws IOException {

        // Import Components //



        // Chart Stage Setup //
        FXMLLoader chartsFXML = new FXMLLoader(HelloApplication.class.getResource("charts.fxml"));
        //
        Scene charts = new Scene(chartsFXML.load(), 1600, 900);
        //
        charts.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("stylesheet.css")).toExternalForm());
        //
        ChartsController controller = chartsFXML.getController();
        //
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("1o8zqjm9bqc81.png")).openStream()));
        stage.setResizable(true);
        stage.setTitle("Stonks");
        stage.setScene(charts);
        stage.show();












        // Resizing handlers //
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            double width = newVal.doubleValue();
            double height = charts.getHeight() - 30;
            controller.stockGraph.setPrefSize(width, height);
            controller.stockGraph.setMaxWidth(width - 200);
        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            double height = newVal.doubleValue() - 70;
            double width = charts.getWidth() - 150;
            controller.stockGraph.setPrefSize(width, height);
        });


    }



    public static void main(String[] args) {
        launch();
    }


}
