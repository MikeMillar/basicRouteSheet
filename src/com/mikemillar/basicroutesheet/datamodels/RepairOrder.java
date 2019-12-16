package com.mikemillar.basicroutesheet.datamodels;

import java.time.LocalDateTime;

public class RepairOrder {
    
    private enum statusOptions {NO_STATUS, ATTN_TECH, ATTN_ADV, ATTN_PARTS, TECH_WORKING, VEH_COMPLETE,
        VEH_READY_FOR_DEL, VEH_PICK_UP, PARTS_WORKING, PARTS_HOLD, AUTH_HOLD, REC_DECLINED}
    
    private int repairOrderNumber;
    private String tagNumber;
    private int year;
    private String make;
    private String model;
    private String mileage;
    private String customerName;
    private String phoneNumber;
    private statusOptions status;
    private String jobs;
    private String adviser;
    private String tech;
    private LocalDateTime timeCreated;
    private LocalDateTime elapsedTime;
    private LocalDateTime timeClosed;
    private String timeDue;
    private String notes;
    private boolean isWaiting;
    
    public RepairOrder(String roNum, String tag, String year, String make, String model, String mileage,
                       String custName, String phoneNum, String jobs, String adviser, String tech,
                       String timeDue, String notes, String waiter) {
        this.repairOrderNumber = parseInt(roNum);
        this.tagNumber = tag;
        this.year = parseInt(year);
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.customerName = custName;
        this.phoneNumber = phoneNum;
        this.jobs = jobs;
        this.adviser = adviser;
        this.tech = tech;
        this.timeCreated = LocalDateTime.now();
        this.timeDue = timeDue;
        this.notes = notes;
        setWaiting(waiter);
        this.status = statusOptions.NO_STATUS;
    }
    
    public int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Unable to parse int from string");
            e.printStackTrace();
            return -1;
        }
    }
    
    public int getRepairOrderNumber() {
        return repairOrderNumber;
    }
    
    public void setRepairOrderNumber(int repairOrderNumber) {
        this.repairOrderNumber = repairOrderNumber;
    }
    
    public String getTagNumber() {
        return tagNumber;
    }
    
    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getMake() {
        return make;
    }
    
    public void setMake(String make) {
        this.make = make;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getMileage() {
        return mileage;
    }
    
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public statusOptions getStatus() {
        return status;
    }
    
    public void setStatus(statusOptions status) {
        this.status = status;
    }
    
    public String getJobs() {
        return jobs;
    }
    
    public void setJobs(String jobs) {
        this.jobs = jobs;
    }
    
    public String getAdviser() {
        return adviser;
    }
    
    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }
    
    public String getTech() {
        return tech;
    }
    
    public void setTech(String tech) {
        this.tech = tech;
    }
    
    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }
    
    public LocalDateTime getElapsedTime() {
        return elapsedTime;
    }
    
    public LocalDateTime getTimeClosed() {
        return timeClosed;
    }
    
    public void setTimeClosed(LocalDateTime timeClosed) {
        this.timeClosed = timeClosed;
    }
    
    public String getTimeDue() {
        return timeDue;
    }
    
    public void setTimeDue(String timeDue) {
        this.timeDue = timeDue;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public boolean isWaiting() {
        return isWaiting;
    }
    
    public void setWaiting(String waiting) {
        System.out.println(waiting);
        if (waiting.equals("Yes")) {
            System.out.println("Setting waiter to true");
            isWaiting = true;
        } else if (waiting.equals("No")) {
            System.out.println("Setting waiter to false");
            isWaiting = false;
        }
    }
}
