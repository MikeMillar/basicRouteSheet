package com.mikemillar.basicroutesheet;

import com.mikemillar.basicroutesheet.datamodels.Note;
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

    @FXML private TextArea displayBox;
    @FXML private TextArea inputField;
    
    @FXML private TextField singletonEditTextField;
    
    @FXML private ComboBox editDueTime;
    
    @FXML private TextField updateCustomerName;
    @FXML private TextField updateCustomerNumber;
    
    @FXML private TextField updateYear;
    @FXML private TextField updateMake;
    @FXML private TextField updateModel;
    @FXML private TextField updateMileage;

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
    
    public void loadRepairOrder(RepairOrder ro) {
        roNumField.setText("" + ro.getRepairOrderNumber());
        tagNumField.setText(ro.getTagNumber());
        yearField.setText("" + ro.getYear());
        makeField.setText(ro.getMake());
        modelField.setText(ro.getModel());
        mileField.setText(ro.getMileage());
        customerNameField.setText(ro.getCustomerName());
        phoneNumField.setText(ro.getPhoneNumber());
        jobsField.setText(ro.getJobs());
        adviserField.setText(ro.getAdviser());
        techField.setText(ro.getTech());
        notesField.setText(ro.getNotes());
        dueTime.setValue(ro.getTimeDue());
        waiterField.setValue(ro.isWaiting());
    }
    
    public void updateRepairOrder(RepairOrder ro) {
        int roNum = Integer.parseInt(roNumField.getText().trim());
        String tag = tagNumField.getText().trim();
        int year = Integer.parseInt(yearField.getText().trim());
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
        if (roNum > 9999 || !tag.isEmpty() || year > 999 || !make.isEmpty() ||
            !model.isEmpty() || !mile.isEmpty() || !customer.isEmpty() || !phone.isEmpty() ||
            !jobs.isEmpty() || !adviser.isEmpty() || !tech.isEmpty() || !notes.isEmpty() ||
            !due.isEmpty() || !waiter.isEmpty()) {
            ro.setRepairOrderNumber(roNum);
            ro.setTagNumber(tag);
            ro.setYear(year);
            ro.setMake(make);
            ro.setModel(model);
            ro.setMileage(mile);
            ro.setCustomerName(customer);
            ro.setPhoneNumber(phone);
            ro.setJobs(jobs);
            ro.setAdviser(adviser);
            ro.setTech(tech);
            ro.addNote(notes);
            ro.setTimeDue(due);
            ro.setWaiting(waiter);
        }
    }

    public void loadNotes(RepairOrder ro) {
        displayBox.setText(ro.getNotes());
    }

    public void addNote(RepairOrder ro) {
        String noteToAdd = inputField.getText().trim();
        if (!noteToAdd.isEmpty()) {
            ro.addNote(noteToAdd);
        }
    }
    
    public void loadAdviser(RepairOrder ro) {
        singletonEditTextField.setText(ro.getAdviser());
    }
    
    public void updateAdviser(RepairOrder ro) {
        String newAdviser = singletonEditTextField.getText().trim();
        if (!newAdviser.isEmpty()) {
            ro.setAdviser(newAdviser);
        }
    }
    
    public void loadTech(RepairOrder ro) {
        singletonEditTextField.setText(ro.getTech());
    }
    
    public void updateTech(RepairOrder ro) {
        String newTech = singletonEditTextField.getText().trim();
        if (!newTech.isEmpty()) {
            ro.setTech(newTech);
        }
    }
    
    public void updateDueTime(RepairOrder ro) {
        String newDueTime = editDueTime.getValue().toString();
        if (newDueTime != null || !newDueTime.isEmpty()) {
            ro.setTimeDue(newDueTime);
        }
    }
    
    public void loadCustomer(RepairOrder ro) {
        updateCustomerName.setText(ro.getCustomerName());
        updateCustomerNumber.setText(ro.getPhoneNumber());
    }
    
    public void updateCustomer(RepairOrder ro) {
        String newName = updateCustomerName.getText().trim();
        String newPhone = updateCustomerNumber.getText().trim();
        
        if (!newName.isEmpty() || !newPhone.isEmpty()) {
            ro.setCustomerName(newName);
            ro.setPhoneNumber(newPhone);
        }
    }
    
    public void loadVehicle(RepairOrder ro) {
        updateYear.setText("" + ro.getYear());
        updateMake.setText(ro.getMake());
        updateModel.setText(ro.getModel());
        updateMileage.setText(ro.getMileage());
    }
    
    public void updateVehicle(RepairOrder ro) {
        int newYear = Integer.parseInt(updateYear.getText().trim());
        String newMake = updateMake.getText().trim();
        String newModel = updateModel.getText().trim();
        String newMileage = updateMileage.getText().trim();
        
        if (newYear > 999 || !newMake.isEmpty() || !newModel.isEmpty() || !newMileage.isEmpty()) {
            ro.setYear(newYear);
            ro.setMake(newMake);
            ro.setModel(newModel);
            ro.setMileage(newMileage);
        }
    }
}
