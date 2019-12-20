package com.mikemillar.basicroutesheet.datamodels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a MM/dd/yyyy");
    
    private RepairOrder ro;
    private String note;
    private LocalDateTime timeCreated;
    private String timeCreatedString;
    
    public Note(RepairOrder ro, String note) {
        this.ro = ro;
        this.note = note;
        this.timeCreated = LocalDateTime.now();
        this.timeCreatedString = this.timeCreated.format(formatter);
    }
    
    public void setNote(String note) {
        this.note = note;
        this.timeCreated = LocalDateTime.now();
        this.timeCreatedString = this.timeCreated.format(formatter);
    }
    
    public RepairOrder getRo() {
        return ro;
    }
    
    public String getNote() {
        return note;
    }
    
    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }
    
    public String getTimeCreatedString() {
        return timeCreatedString;
    }

    public void addNote(String note) {
        this.note = this.note + "\n\n" + note;
    }
}
