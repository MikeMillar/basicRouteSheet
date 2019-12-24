package com.mikemillar.basicroutesheet.datamodels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class RepairOrder {
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a MM-dd-yyyy");
    
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
    private LocalDateTime tCreated;
    private String timeCreated;
    private String elapsedTime;
    private LocalDateTime tClosed;
    private String timeClosed;
    private String elapsedClosed;
    private String timeDue;
    private Note notes;
//    private String notes;
    private boolean isWaiting;
    private boolean isClosed;
    private boolean isLocked;
    
    public RepairOrder() {
    
    }
    
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
        this.tCreated = LocalDateTime.now();
        this.timeCreated = this.tCreated.format(formatter);
        this.tClosed = LocalDateTime.now().plusYears(5);
        this.timeDue = timeDue;
        this.notes = new Note(this, notes);
        setWaiting(waiter);
        this.status = statusOptions.NO_STATUS;
        setElapsedTime();
        this.isClosed = false;
        this.isLocked = false;
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
    
    public boolean openRepairOrder() {
        if (!isLocked) {
            this.isClosed = false;
            this.tClosed = tCreated.plusYears(5);
            this.timeClosed = tClosed.format(formatter);
            this.elapsedClosed = "";
            return true;
        }
        return false;
    }
    
    public void closeRepairOrder() {
        this.isClosed = true;
        this.tClosed = LocalDateTime.now();
        this.timeClosed = tClosed.format(formatter);
    }
    
    public boolean isClosed() {
        return isClosed;
    }
    
    public void setClosed(boolean closed) {
        isClosed = closed;
    }
    
    public boolean isLocked() {
        return isLocked;
    }
    
    public void setLocked(boolean locked) {
        isLocked = locked;
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
    
    public String getStatus() {
        switch (status) {
            case NO_STATUS:
                return "No Status";
            case ATTN_ADV:
                return "Attention Adviser";
            case ATTN_TECH:
                return "Attention Tech";
            case AUTH_HOLD:
                return "Authorization Hold";
            case ATTN_PARTS:
                return "Attention Parts";
            case PARTS_HOLD:
                return "Parts Hold";
            case VEH_PICK_UP:
                return "Vehicle Picked Up";
            case REC_DECLINED:
                return "Declined Recommendations";
            case TECH_WORKING:
                return "Tech Working on Vehicle";
            case VEH_COMPLETE:
                return "Vehicle Complete";
            case PARTS_WORKING:
                return "Parts Working";
            case VEH_READY_FOR_DEL:
                return "Vehicle ready for delivery";
        }
        return null;
    }
    
    public void setStatus(statusOptions status) {
        this.status = status;
    }
    
    public void setStatus(String status) {
        // add way to set status via string
        try {
            switch (status) {
                case "No Status":
                    this.status = statusOptions.NO_STATUS;
                    break;
                case "Attention Adviser":
                    this.status = statusOptions.ATTN_ADV;
                    break;
                case "Attention Tech":
                    this.status = statusOptions.ATTN_TECH;
                    break;
                case "Authorization Hold":
                    this.status = statusOptions.AUTH_HOLD;
                    break;
                case "Attention Parts":
                    this.status = statusOptions.ATTN_PARTS;
                    break;
                case "Parts Hold":
                    this.status = statusOptions.PARTS_HOLD;
                    break;
                case "Vehicle Picked Up":
                    this.status = statusOptions.VEH_PICK_UP;
                    break;
                case "Declined Recommendations":
                    this.status = statusOptions.REC_DECLINED;
                    break;
                case "Tech Working on Vehicle":
                    this.status = statusOptions.TECH_WORKING;
                    break;
                case "Vehicle Complete":
                    this.status = statusOptions.VEH_COMPLETE;
                    break;
                case "Parts Working":
                    this.status = statusOptions.PARTS_WORKING;
                    break;
                case "Vehicle ready for delivery":
                    this.status = statusOptions.VEH_READY_FOR_DEL;
                    break;
                default:
                    System.out.println("Unable to set status. Incorrect data passed");
            }
        } catch (NullPointerException e) {
            System.out.println("Unable to set status. No data found");
            e.printStackTrace();
        }
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
    
    public LocalDateTime getTCreated() {
        return tCreated;
    }
    
    public String getTimeCreated() {
        return timeCreated;
    }
    
    public String getTCreatedString() {
        return tCreated.toString();
    }
    
    public String getElapsedTime() {
        return elapsedTime;
    }
    
    public LocalDateTime getTClosed() {
        return tClosed;
    }
    
    public String getTimeClosed() {
        return timeClosed;
    }
    
    public String getTClosedString() {
        return tClosed.toString();
    }
    
    public void setTClosed(LocalDateTime timeClosed) {
        this.tClosed = timeClosed;
    }
    
    public void setTimeClosed(String timeClosed) {
        try {
            this.tClosed = LocalDateTime.parse(timeClosed);
            this.setTClosed(tClosed);
            this.setElapsedClosed();
        } catch (DateTimeParseException e) {
            // nothing
            e.printStackTrace();
        }
    }
    
    public String getTimeDue() {
        return timeDue;
    }
    
    public void setTimeDue(String timeDue) {
        this.timeDue = timeDue;
    }
    
    public String getNotes() {
        try {
            return notes.getNote();
        } catch (NullPointerException e) {
            System.out.println("Unable to fetch notes. no notes stored");
            return null;
        }
    }
    
    public void setNotes(String notes) {
        try {
            if (this.notes == null) {
                this.notes = new Note(this, notes);
            } else {
                this.notes.setNote(notes);
            }
        } catch (NullPointerException e) {
            System.out.println("Unable to set note");
            e.printStackTrace();
        }
    }
    
    public String isWaiting() {
        if (isWaiting) {
            return "Yes";
        } else {
            return "No";
        }
    }
    
    public void setWaiting(String waiting) {
        if (waiting.equals("Yes")) {
            isWaiting = true;
        } else if (waiting.equals("No")) {
            isWaiting = false;
        }
    }
    
    public void setTCreated(LocalDateTime timeCreated) {
        this.tCreated = timeCreated;
    }
    
    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated.format(formatter);
    }
    
    public void setTClosed(String timeClosed) {
        this.timeClosed = timeClosed;
    }
    
    public void setTimeCreated(String timeCreated) {
        // Add a way to save variable
        this.tCreated = LocalDateTime.parse(timeCreated);
        this.setTimeCreated(tCreated);
        this.setElapsedTime();
    }
    
    public void setElapsedTime() {
        LocalDateTime now = LocalDateTime.now();
        
        LocalDateTime fromTemp = LocalDateTime.from(tCreated);
        long years = fromTemp.until(now, ChronoUnit.YEARS);
        fromTemp = fromTemp.plusYears(years);
        
        long months = fromTemp.until(now, ChronoUnit.MONTHS);
        fromTemp = fromTemp.plusMonths(months);
        
        long days = fromTemp.until(now,ChronoUnit.DAYS);
        fromTemp = fromTemp.plusDays(days);
        
        long hours = fromTemp.until(now,ChronoUnit.HOURS);
        fromTemp = fromTemp.plusHours(hours);
        
        long minutes = fromTemp.until(now,ChronoUnit.MINUTES);
        fromTemp = fromTemp.plusMinutes(minutes);
        
        long seconds = fromTemp.until(now,ChronoUnit.SECONDS);
        fromTemp = fromTemp.plusSeconds(seconds);
        
        if (years > 0) {
            elapsedTime = String.format("%s years, %s months", years, months);
        } else if (months > 0) {
            elapsedTime = String.format("%s months, %s days", months, days);
        } else if (days > 0) {
            elapsedTime = String.format("%s days, %s hours", days, hours);
        } else if (hours > 0) {
            elapsedTime = String.format("%s hours, %s minutes", hours, minutes);
        } else {
            elapsedTime = String.format("%s minutes, %s seconds", minutes, seconds);
        }
    }
    
    public void setLocked() {
        if (this.tClosed != null) {
            LocalDateTime now = LocalDateTime.now();
    
            LocalDateTime fromTemp = LocalDateTime.from(tClosed);
            long years = fromTemp.until(now, ChronoUnit.YEARS);
            fromTemp = fromTemp.plusYears(years);
    
            long months = fromTemp.until(now, ChronoUnit.MONTHS);
            fromTemp = fromTemp.plusMonths(months);
    
            long days = fromTemp.until(now,ChronoUnit.DAYS);
            fromTemp = fromTemp.plusDays(days);
    
            if (days > 0) {
                this.isLocked = true;
            }
        }
    }
    
    public boolean removeFromSOP() {
        LocalDateTime now = LocalDateTime.now();
        
        if (this.tClosed != null) {
            LocalDateTime fromTemp = LocalDateTime.from(tClosed);
            long years = fromTemp.until(now, ChronoUnit.YEARS);
            fromTemp = fromTemp.plusYears(years);
    
            long months = fromTemp.until(now, ChronoUnit.MONTHS);
            fromTemp = fromTemp.plusMonths(months);
            
            if (months > 0) {
                return true;
            }
        }
        return false;
    }
    
    public void setElapsedClosed() {
        if (this.tClosed != null) {
            LocalDateTime now = LocalDateTime.now();
            
            LocalDateTime fromTemp = LocalDateTime.from(tClosed);
            long years = fromTemp.until(now, ChronoUnit.YEARS);
            fromTemp = fromTemp.plusYears(years);
    
            long months = fromTemp.until(now, ChronoUnit.MONTHS);
            fromTemp = fromTemp.plusMonths(months);
    
            long days = fromTemp.until(now, ChronoUnit.DAYS);
            fromTemp = fromTemp.plusDays(days);
    
            long hours = fromTemp.until(now, ChronoUnit.HOURS);
            fromTemp = fromTemp.plusHours(hours);
    
            long minutes = fromTemp.until(now, ChronoUnit.MINUTES);
            fromTemp = fromTemp.plusMinutes(minutes);
    
            long seconds = fromTemp.until(now, ChronoUnit.SECONDS);
            fromTemp = fromTemp.plusSeconds(seconds);
    
            if (years > 0) {
                elapsedClosed = String.format("%s years, %s months", years, months);
            } else if (months > 0) {
                elapsedClosed = String.format("%s months, %s days", months, days);
            } else if (days > 0) {
                elapsedClosed = String.format("%s days, %s hours", days, hours);
            } else if (hours > 0) {
                elapsedClosed = String.format("%s hours, %s minutes", hours, minutes);
            } else {
                elapsedClosed = String.format("%s minutes, %s seconds", minutes, seconds);
            }
        }
    }
    

    public void addNote(String note) {
        this.notes.addNote(note);
    }
}
