package com.mikemillar.basicroutesheet;

import com.mikemillar.basicroutesheet.datamodels.RepairOrder;
import com.mikemillar.basicroutesheet.datamodels.RepairOrderData;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DialogController {
    
    @FXML private TextField roNumField;
    @FXML private TextField tagNumField;
    @FXML private TextField yearField;
    @FXML private TextField makeField;
    @FXML private TextField modelField;
    @FXML private TextField mileField;
    @FXML private TextField customerNameField;
    @FXML private TextField phoneNumField;
    @FXML private TextField jobsField;
    @FXML private TextField adviserField;
    @FXML private TextField techField;
    @FXML private TextArea notesField;
    @FXML private ComboBox dueTime;
    @FXML private ComboBox waiterField;
    
    
    public RepairOrder addRO() {
        String roNum = roNumField.getText().trim();
        String tag = tagNumField.getText().trim();
        String year = yearField.getText().trim();
        String make = makeField.getText().trim();
        String model = modelField.getText().trim();
        String mile = mileField.getText().trim();
        String customer = customerNameField.getText().trim();
        String phone = phoneNumField.getText().trim();
        String jobs = jobsField.getText().trim();
        String adviser = adviserField.getText().trim();
        String tech = techField.getText().trim();
        String notes = notesField.getText().trim();
        String due = dueTime.getValue().toString();
        String waiter = waiterField.getValue().toString();
        RepairOrder ro = new RepairOrder(roNum,tag,year,make, model,mile, customer, phone, jobs, adviser, tech, due, notes, waiter);
        RepairOrderData.getInstance().addToList(ro,RepairOrderData.roList);
        return ro;
    }
}
