package main.java.app;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

import java.time.DayOfWeek;

public class Assignments extends RecursiveTreeObject<Assignments> {
    public String title;
    public String dueDate;
    public String priority;

    public Assignments(String title, String dueDate, String priority) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
    }


}
