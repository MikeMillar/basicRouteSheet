package com.mikemillar.basicroutesheet.datamodels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RepairOrderData {
    
    private static RepairOrderData instance = new RepairOrderData();
    
    private static ObservableList<RepairOrder> roList;
    private static ObservableList<RepairOrder> inactiveList;
    private static ObservableList<RepairOrder> sopInactiveList;
    
    private RepairOrderData() {
        roList = FXCollections.observableArrayList();
        inactiveList = FXCollections.observableArrayList();
        sopInactiveList = FXCollections.observableArrayList();
    }
    
    public RepairOrderData getInstance() {
        return instance;
    }
    
    public void addToList(RepairOrder ro, ObservableList<RepairOrder> list) {
        try {
            list.add(ro);
        } catch (NullPointerException e) {
            System.out.println("Unable to add RO to list. Incorrect RO / List");
            e.printStackTrace();
        }
    }
    
    public void removeFromList(RepairOrder ro, ObservableList<RepairOrder> list) {
        try {
            list.remove(ro);
        } catch (NullPointerException e) {
            System.out.println("Unable to remove RO from list. Incorrect RO / List");
            e.printStackTrace();
        }
    }
    
    public void replaceFromList(RepairOrder ro, ObservableList<RepairOrder> list) {
        try {
            int index = list.indexOf(ro);
            list.set(index, ro);
        } catch (NullPointerException e) {
            System.out.println("Unable to replace RO from list. Incorrect RO / List");
            e.printStackTrace();
        }
    }
    
    public static ObservableList<RepairOrder> getRoList() {
        return roList;
    }
    
    public static ObservableList<RepairOrder> getInactiveList() {
        return inactiveList;
    }
    
    public static ObservableList<RepairOrder> getSopInactiveList() {
        return sopInactiveList;
    }
}
