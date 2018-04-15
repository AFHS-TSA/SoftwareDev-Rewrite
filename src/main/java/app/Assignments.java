package main.java.app;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

import java.time.DayOfWeek;

public class Assignments extends RecursiveTreeObject<Assignments> {
    public SimpleStringProperty title;
    public SimpleStringProperty dueDate;
    public SimpleStringProperty priority;

    public Assignments(String title, String dueDate, String priority) {
        this.title = new SimpleStringProperty(title);
        this.dueDate = new SimpleStringProperty(dueDate);
        this.priority = new SimpleStringProperty(priority);
    }

}
