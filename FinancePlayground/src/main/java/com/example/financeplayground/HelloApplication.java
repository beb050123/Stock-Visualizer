package com.example.financeplayground;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        // Stage setup //
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        scene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("stylesheet.css")).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("1o8zqjm9bqc81.png")).openStream()));
        stage.setResizable(true);
        stage.setTitle("Stonks");
        stage.setScene(scene);
        stage.show();
        HelloController controller = fxmlLoader.getController();


        // Resizing handlers //
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            double width = newVal.doubleValue();
            double height = scene.getHeight() - 30;
            controller.stockGraph.setPrefSize(width, height);
            controller.stockGraph.setMaxWidth(width - 200);

        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            double height = newVal.doubleValue() - 70;
            double width = scene.getWidth() - 150;
            controller.stockGraph.setPrefSize(width, height);

        });


    }

    public static void main(String[] args) {
        launch();
    }


}
