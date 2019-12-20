package com.mikemillar.basicroutesheet;

import com.mikemillar.basicroutesheet.datamodels.Refresher;
import com.mikemillar.basicroutesheet.datamodels.RepairOrder;
import com.mikemillar.basicroutesheet.datamodels.RepairOrderData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class Controller {
    
    public Refresher refresher;
    
    @FXML private BorderPane mainBorderPane;
    @FXML private TableView<RepairOrder> activeTable;

    public void initialize() {
        activeTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        activeTable.getSelectionModel().selectFirst();
        activeTable.setItems(RepairOrderData.getRoList());
        refresher = new Refresher(this,60000,60000);
        Main.setRefresher(refresher);
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

    public void showNotesDialog() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Repair Order Notes");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("notesDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        DialogController controller = fxmlLoader.getController();
        controller.loadNotes(ro);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
//        if (result.isPresent() && result.get() == ButtonType.APPLY) {
//            controller.addNote(ro);
//        }
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.addNote(ro);
        }
    }
    
    public void setStatus(ActionEvent e) {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        String srcText = ((MenuItem)e.getSource()).getText();
        ro.setStatus(srcText);
        refreshList();
    }

    public void refreshList() {
        RepairOrderData.getInstance().updateList();
        activeTable.refresh();
    }
    
    public void handleSave() {
        RepairOrderData.getInstance().saveLists();
    }
    
    public void handleExit() {
        refresher.end();
        Platform.exit();
    }
    
}
