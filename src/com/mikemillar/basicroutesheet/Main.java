package com.mikemillar.basicroutesheet;

import com.mikemillar.basicroutesheet.datamodels.Refresher;
import com.mikemillar.basicroutesheet.datamodels.RepairOrderData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    private static Refresher mainRefresher;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Simple Route Sheet");
        primaryStage.setScene(new Scene(root, 1024, 760));
        primaryStage.show();
    }
    
    @Override
    public void init() throws Exception {
        RepairOrderData.getInstance().loadLists();
    }

    @Override
    public void stop() throws Exception {
        mainRefresher.end();
        RepairOrderData.getInstance().saveLists();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void setRefresher(Refresher refresher) {
        mainRefresher = refresher;
    }
}
