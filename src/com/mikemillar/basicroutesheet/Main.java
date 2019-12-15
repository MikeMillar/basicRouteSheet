package com.mikemillar.basicroutesheet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Simple Route Sheet");
        primaryStage.setScene(new Scene(root, 1024, 760));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
