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
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1920, 1000);
    scene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("stylesheet.css")).toExternalForm());
    stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("1o8zqjm9bqc81.png")).openStream()));
    stage.setResizable(false);
    stage.setTitle("Stonks");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {

    launch();
  }

  public static void dynamicLayout(Scene scene){

  }


}
