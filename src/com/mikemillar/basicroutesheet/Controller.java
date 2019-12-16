package com.mikemillar.basicroutesheet;

import com.mikemillar.basicroutesheet.datamodels.RepairOrder;
import com.mikemillar.basicroutesheet.datamodels.RepairOrderData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    
    @FXML private BorderPane mainBorderPane;
    @FXML private TableView activeTable;

    public void initialize() {
        activeTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        activeTable.setItems(RepairOrderData.getRoList());
    }
    
    public void showNewRODialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Repair Order");
        dialog.setHeaderText("Use this dialog to add a new repair order");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newRepairOrderDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }
        
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            RepairOrder newRO = controller.addRO();
        }
    }
    
    public void handleExit() {
        Platform.exit();
    }
    
}
