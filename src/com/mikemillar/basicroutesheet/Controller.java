package com.mikemillar.basicroutesheet;

import com.mikemillar.basicroutesheet.datamodels.Refresher;
import com.mikemillar.basicroutesheet.datamodels.RepairOrder;
import com.mikemillar.basicroutesheet.datamodels.RepairOrderData;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    
    public Refresher refresher;
    
    @FXML private BorderPane mainBorderPane;
    @FXML private TableView<RepairOrder> activeTable;
    @FXML private RadioButton activeListButton;
    @FXML private RadioButton orderListButton;
    @FXML private RadioButton closedListButton;

    public void initialize() {
        activeTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        activeTable.getSelectionModel().selectFirst();
        activeTable.setItems(RepairOrderData.getRoList());
        refresher = new Refresher(this,60000,60000);
        Main.setRefresher(refresher);
        refreshList();
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
    
    public void showEditAdviserDialog() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Assign Adviser to Repair Order");
        dialog.setHeaderText("Type name of adviser.");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("singleTextFieldEdit.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }
        
        DialogController controller = fxmlLoader.getController();
        controller.loadAdviser(ro);
        
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.updateAdviser(ro);
            refreshList();
        }
    }
    
    public void showEditTechDialog() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Assign Technician to Repair Order");
        dialog.setHeaderText("Type name of Technician.");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("singleTextFieldEdit.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        DialogController controller = fxmlLoader.getController();
        controller.loadTech(ro);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.updateTech(ro);
            refreshList();
        }
    }

    public void showEditDueTimeDialog() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit Repair Order Due Time");
        dialog.setHeaderText("Select new due time.");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("dueTimeDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        DialogController controller = fxmlLoader.getController();

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.updateDueTime(ro);
            refreshList();
        }
    }

    public void showEditCustomerDialog() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit Customer Information");
        dialog.setHeaderText("Type updated customer information");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("customerDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        DialogController controller = fxmlLoader.getController();
        controller.loadCustomer(ro);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.updateCustomer(ro);
            refreshList();
        }
    }

    public void showEditVehicleDialog() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit Vehicle Information");
        dialog.setHeaderText("Type updated vehicle informating");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("vehicleDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }

        DialogController controller = fxmlLoader.getController();
        controller.loadVehicle(ro);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.updateVehicle(ro);
            refreshList();
        }
    }
    
    public void showEditAllDialog() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit All Repair Order Information");
        dialog.setHeaderText("Type Updated Repair Order Information");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("newRepairOrderDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
            return;
        }
        
        DialogController controller = fxmlLoader.getController();
        controller.loadRepairOrder(ro);
        
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.updateRepairOrder(ro);
            refreshList();
        }
    }
    
    public void setRepairOrderActive() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        if (ro.openRepairOrder()) {
            if (orderListButton.isSelected()) {
                RepairOrderData.getInstance().addToList(ro, RepairOrderData.getRoList());
                RepairOrderData.getInstance().removeFromList(ro, RepairOrderData.getSopInactiveList());
            } else if (closedListButton.isSelected()) {
                RepairOrderData.getInstance().addToList(ro, RepairOrderData.getRoList());
                RepairOrderData.getInstance().removeFromList(ro, RepairOrderData.getInactiveList());
            }
        } else {
            System.out.println("Unable to Open Repair Order. Repair Order Locked.");
        }
    }
    
    public void setInactiveSOP() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        ro.closeRepairOrder();
        if (activeListButton.isSelected()) {
            RepairOrderData.getInstance().addToList(ro,RepairOrderData.getSopInactiveList());
            RepairOrderData.getInstance().removeFromList(ro,RepairOrderData.getRoList());
        } else if (closedListButton.isSelected()) {
            RepairOrderData.getInstance().addToList(ro,RepairOrderData.getSopInactiveList());
            RepairOrderData.getInstance().removeFromList(ro,RepairOrderData.getInactiveList());
        }
    }
    
    public void voidRepairOrder() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Void Current Repair Order?");
        alert.setHeaderText("Are you sure you want to void Repair Order" + ro.getRepairOrderNumber() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            RepairOrderData.getInstance().removeFromList(ro,RepairOrderData.getRoList());
            refreshList();
        }
    }
    
    public void closeRepairOrder() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        ro.closeRepairOrder();
        if (activeListButton.isSelected()) {
            RepairOrderData.getInstance().addToList(ro, RepairOrderData.getInactiveList());
            RepairOrderData.getInstance().removeFromList(ro, RepairOrderData.getRoList());
        } else if (orderListButton.isSelected()) {
            RepairOrderData.getInstance().addToList(ro, RepairOrderData.getInactiveList());
            RepairOrderData.getInstance().removeFromList(ro, RepairOrderData.getSopInactiveList());
        }
    }
    
    public void toggleList(ActionEvent e) {
        String s = ((RadioButton)e.getSource()).getText();
        if (s.equals("Active RO List")) {
            activeTable.setItems(RepairOrderData.getRoList());
        } else if (s.equals("Inactive SOP List")) {
            activeTable.setItems(RepairOrderData.getSopInactiveList());
        } else if (s.equals("Closed RO List")) {
            activeTable.setItems(RepairOrderData.getInactiveList());
        }
    }
    
    public void setStatus(ActionEvent e) {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        String srcText = ((MenuItem)e.getSource()).getText();
        ro.setStatus(srcText);
        refreshList();
    }
    
    public void setRowStyle() {
        activeTable.setRowFactory(row -> new TableRow<RepairOrder>() {
            @Override
            public void updateItem(RepairOrder ro, boolean empty) {
                super.updateItem(ro,empty);

                if (ro == null || empty) {
                    setStyle("");
                } else {
                    switch (ro.getStatus()) {
                        case "No Status":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-text-fill: black; -fx-background-color: white");
                            }
                            break;
                        case "Attention Adviser":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: blue");
                            }
                            break;
                        case "Attention Tech":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: green");
                            }
                            break;
                        case "Authorization Hold":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: purple");
                            }
                            break;
                        case "Attention Parts":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: yellow");
                            }
                            break;
                        case "Parts Hold":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: brown");
                            }
                            break;
                        case "Vehicle Picked Up":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: DimGrey");
                            }
                            break;
                        case "Declined Recommendations":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: red");
                            }
                            break;
                        case "Tech Working on Vehicle":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: DarkGreen");
                            }
                            break;
                        case "Vehicle Complete":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: DarkCyan");
                            }
                            break;
                        case "Parts Working":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: DarkKhaki");
                            }
                            break;
                        case "Vehicle ready for delivery":
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle("-fx-background-color: DarkGrey");
                            }
                            break;
                        default:
                            for (int i = 0; i < getChildren().size(); i++) {
                                getChildren().get(i).setStyle(null);
                            }
                    }
                }

            }
        });
    }
    
    public void clearStatus() {
        RepairOrder ro = activeTable.getSelectionModel().getSelectedItem();
        if (ro != null) {
            ro.setStatus("No Status");
            refreshList();
        } else {
            System.out.println("Unable to clear status");
        }
    }

    public void refreshList() {
        RepairOrderData.getInstance().updateList();
//        setRowStyle(); --- disabled until further research can be done to solve issues
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
