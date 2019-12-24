package com.mikemillar.basicroutesheet.datamodels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a MM-dd-yyyy");
    
    private RepairOrder ro;
    private String note;
    
    public Note(RepairOrder ro, String note) {
        this.ro = ro;
        this.note = note;
        System.out.println("Constructor note to set: " + note);
    }
    
    public void setNote(String note) {
        System.out.println("Note to load: " + note);
        this.note = note;
    }
    
    public RepairOrder getRo() {
        return ro;
    }
    
    public String getNote() {
        return note;
    }

    public void addNote(String note) {
        this.note = this.note + "\n" + LocalDateTime.now().format(formatter);
        this.note = this.note + "\n" + note;
    }
}
