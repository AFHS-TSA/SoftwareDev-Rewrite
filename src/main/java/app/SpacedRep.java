package main.java.app;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SpacedRep {
    double time1 = 0;
    double time2 = .041;
    double memStrength = 1;
    int n = 0;
    int rN = 0;
    int d = 0;
    String title;

    ZoneId zone = ZoneId.systemDefault();
    ZonedDateTime time = ZonedDateTime.now(zone);


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int SM2() {
        //d = 0;
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
        //memStrength++;
        return d;
    }

    public int getHour() {
        time = ZonedDateTime.now(zone);
        return (time.getHour() + d) % 24 > 12 ? ((time.getHour() + d) % 24) - 12 : (time.getHour() + d) % 24;
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
