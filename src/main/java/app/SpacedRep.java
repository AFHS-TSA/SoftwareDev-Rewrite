package main.java.app;

import javafx.scene.control.Label;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SpacedRep {
    static double time1 = 0;
    static double time2 = .041;
    private static double memStrength = 1;
    static int n = 0;
    static int rN = 0;
    static int d = 0;
    String name;
    Label lbl = new Label();

    ZoneId zone = ZoneId.systemDefault();
    ZonedDateTime time = ZonedDateTime.now(zone);

    public SpacedRep() {

    }

    public double getMemStrength() {
        return memStrength;
    }

    public Label getLabel() {
        return lbl;
    }

    public void setLabel(String str) {
        lbl.setText(str);
    }

    public void getUpdate() {
        this.SM2();
        this.setLabel("Study " + this.getTitle() + " at " + this.getHour() + ":" + this.getMinute() + " " + this.getMeridiem()/* + " " + this.getDay()*/);
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getTitle() {
        return this.name;
    }

    public static void SM2() {
        d = 0;
        n = 0;
        for (; n < 1;) {
            double exp1 = (-time1 / (memStrength));
            double exp2 = (-time2 / (memStrength));
            double y1 = Math.exp(exp1);
            double y2 = Math.exp(exp2);
            if (y1 - y2 > .3) {
                memStrength++;
                time2 = .041;
                n++;
            } else {
                d++;
                time2 += .041;
            }
        }


        rN = n;
        memStrength++;
        //return d;
    }

    public String getHour() {
        time = ZonedDateTime.now(zone);
        return (time.getHour() + d) % 24 > 12 ? ""+(((time.getHour() + d) % 24) - 12) : ""+(time.getHour() + d) % 24;
    }

    public String getMinute() {
        return time.getMinute() < 10 ? "0"+time.getMinute() : ""+time.getMinute();
    }

    public String getDay() {
        return "on "+time.getMonth()+ " " + (((time.getHour() + d) / 24)+time.getDayOfMonth());
    }

    public String getMeridiem() {
        if ((time.getHour() + d) % 24 < 12) {
            return "AM";
        }
        return "PM";
    }

}
