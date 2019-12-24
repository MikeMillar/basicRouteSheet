package com.mikemillar.basicroutesheet.datamodels;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a MM-dd-yyyy");
    
    private RepairOrder ro;
    private String note;
    
    public Note(RepairOrder ro, String note) {
        this.ro = ro;
        this.note = note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public RepairOrder getRo() {
        return ro;
    }
    
    public String getNote() {
        return note;
    }

    public void addNote(String note) {
        Text dateTime = new Text();
        dateTime.setText(LocalDateTime.now().format(formatter));
        dateTime.setFont(Font.font("Times New Roman", 20));
        this.note = this.note + "\n\n" + LocalDateTime.now().format(formatter);
        this.note = this.note + "\n\n" + note;
    }
}
