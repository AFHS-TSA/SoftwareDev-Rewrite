package main.java.app;

import main.java.app.controllers.RewardsController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Methods {

    public static void updatePoints() {
        Preferences pref = Preferences.userNodeForPackage(RewardsController.class);

        Var.points = pref.getInt("points", 1800);

        try {
            pref.exportNode(new FileOutputStream("Rewards.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public static void setPoints() {
        Preferences pref = Preferences.userNodeForPackage(RewardsController.class);
        pref.putInt("points", Var.points);
        try {
            pref.exportNode(new FileOutputStream("Rewards.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public static void setPlanets() {
        Preferences pref = Preferences.userNodeForPackage(RewardsController.class);
        pref = Preferences.userNodeForPackage(RewardsController.class);
        pref.putBoolean("mercury", Var.mercury);
        pref.putBoolean("venus", Var.venus);
        pref.putBoolean("earth", Var.earth);
        pref.putBoolean("mars", Var.mars);
        pref.putBoolean("jupiter", Var.jupiter);
        pref.putBoolean("saturn", Var.saturn);
        pref.putBoolean("uranus", Var.uranus);
        pref.putBoolean("neptune", Var.neptune);
        try {
            pref.exportNode(new FileOutputStream("Rewards.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public static int getHour(ZonedDateTime time) {
        return (time.getHour()) % 24 > 12 ? ((time.getHour()) % 24) - 12 : (time.getHour()) % 24;
    }

    public static String getMinute(ZonedDateTime time) {
        return time.getMinute() < 10 ? "0"+time.getMinute() : ""+time.getMinute();
    }

    public static String getDay(ZonedDateTime time) {
        return "on "+time.getMonth()+ " " + (((time.getHour()) / 24)+time.getDayOfMonth());
    }

    public static String getMeridiem(ZonedDateTime time) {
        if ((time.getHour()) % 24 < 12) {
            return "AM";
        }
        return "PM";
    }

    public static String getMonth(ZonedDateTime time) {
        String first = time.getMonth().toString().substring(0, 1).toUpperCase();
        String rest = time.getMonth().toString().substring(1).toLowerCase();
        return first+rest;
    }

    public static String getQuote() {
        int randomInt;
        Preferences preferences = Preferences.userNodeForPackage(Methods.class);
        Random random = new Random();
        do {
            randomInt = random.nextInt((24-0) + 1);
        } while (randomInt == Integer.parseInt(preferences.get("RandomNumber", "root")));

        preferences.putInt("RandomNumber", randomInt);
        try {
            preferences.exportNode(new FileOutputStream("quote.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
        return Var.quoteList[randomInt];
    }

    public static ArrayList getUrgent(ZonedDateTime time) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < Var.assignmentsList.size(); i++) {
            if (time.getMonthValue() < 10 && Var.assignmentsList.get(i).dueDate
                    .equals("0" + time.getMonthValue() + "-" + time.getDayOfMonth() + "-" + time.getYear())) {
                list.add(Var.assignmentsList.get(i).title);
            } else if (time.getMonthValue() >= 10 && Var.assignmentsList.get(i).dueDate
                    .equals(time.getMonthValue() + "-" + time.getDayOfMonth() + "-" + time.getYear())) {
                list.add(Var.assignmentsList.get(i).title);
            }
        }
        return list;
    }

}
