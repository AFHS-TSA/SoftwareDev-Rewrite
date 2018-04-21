package main.java.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.java.app.Methods;
import main.java.app.Var;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class RewardsController {


    @FXML
    Label pointslbl;

    @FXML
    ImageView mercury;
    @FXML
    ImageView venus;
    @FXML
    ImageView earth;
    @FXML
    ImageView mars;
    @FXML
    ImageView jupiter;
    @FXML
    ImageView saturn;
    @FXML
    ImageView uranus;
    @FXML
    ImageView neptune;

    @FXML
    Button mercurybtn;
    @FXML
    Button venusbtn;
    @FXML
    Button earthbtn;
    @FXML
    Button marsbtn;
    @FXML
    Button jupiterbtn;
    @FXML
    Button saturnbtn;
    @FXML
    Button uranusbtn;
    @FXML
    Button neptunebtn;
    @FXML
    Button plutobtn;

    Preferences pref;

    @FXML
    private void onMercuryClicked() {
        if (Var.points >= 500) {
            Var.points -= 500;
            Var.mercury = true;
            mercury.setVisible(true);
            pointslbl.setText("Points: " + Var.points);
            updatePoints();
            updatePlanets();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("My Galaxy");
            alert.setHeaderText("Insufficient points to purchase");
            alert.setContentText("You need " + (500 - Var.points) + " more points");

            alert.showAndWait();
        }
    }

    @FXML
    private void onVenusClicked() {
        if (Var.points >= 700) {
            Var.points -= 700;
            Var.venus = true;
            venus.setVisible(true);
            pointslbl.setText("Points: " + Var.points);
            updatePoints();
            updatePlanets();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("My Galaxy");
            alert.setHeaderText("Insufficient points to purchase");
            alert.setContentText("You need " + (700 - Var.points) + " more points");

            alert.showAndWait();
        }
    }

    @FXML
    private void onEarthClicked() {
        if (Var.points >= 900) {
            Var.points -= 900;
            Var.earth = true;
            earth.setVisible(true);
            pointslbl.setText("Points: " + Var.points);
            updatePoints();
            updatePlanets();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("My Galaxy");
            alert.setHeaderText("Insufficient points to purchase");
            alert.setContentText("You need " + (900 - Var.points) + " more points");

            alert.showAndWait();
        }
    }

    @FXML
    private void onMarsClicked() {
        if (Var.points >= 1100) {
            Var.points -= 1100;
            Var.mars = true;
            mars.setVisible(true);
            pointslbl.setText("Points: " + Var.points);
            updatePoints();
            updatePlanets();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("My Galaxy");
            alert.setHeaderText("Insufficient points to purchase");
            alert.setContentText("You need " + (1100 - Var.points) + " more points");

            alert.showAndWait();
        }
    }

    @FXML
    private void onJupiterClicked() {
        if (Var.points >= 1300) {
            Var.points -= 1300;
            Var.jupiter = true;
            jupiter.setVisible(true);
            pointslbl.setText("Points: " + Var.points);
            updatePoints();
            updatePlanets();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("My Galaxy");
            alert.setHeaderText("Insufficient points to purchase");
            alert.setContentText("You need " + (1300 - Var.points) + " more points");

            alert.showAndWait();
        }
    }

    @FXML
    private void onSaturnClicked() {
        if (Var.points >= 1500) {
            Var.points -= 1500;
            Var.saturn = true;
            saturn.setVisible(true);
            pointslbl.setText("Points: " + Var.points);
            updatePoints();
            updatePlanets();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("My Galaxy");
            alert.setHeaderText("Insufficient points to purchase");
            alert.setContentText("You need " + (1500 - Var.points) + " more points");

            alert.showAndWait();
        }
    }

    @FXML
    private void onUranusClicked() {
        if (Var.points >= 1700) {
            Var.points -= 1700;
            Var.uranus = true;
            uranus.setVisible(true);
            pointslbl.setText("Points: " + Var.points);
            updatePoints();
            updatePlanets();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("My Galaxy");
            alert.setHeaderText("Insufficient points to purchase");
            alert.setContentText("You need " + (1700 - Var.points) + " more points");

            alert.showAndWait();
        }
    }

    @FXML
    private void onNeptuneClicked() {
        if (Var.points >= 1900) {
            Var.points -= 1900;
            Var.neptune = true;
            neptune.setVisible(true);
            pointslbl.setText("Points: " + Var.points);
            updatePoints();
            updatePlanets();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("My Galaxy");
            alert.setHeaderText("Insufficient points to purchase");
            alert.setContentText("You need " + (1900 - Var.points) + " more points");

            alert.showAndWait();
        }
    }

    @FXML
    private void onPlutoClicked() {

    }

    @FXML
    public void initialize() {
        pref = Preferences.userNodeForPackage(RewardsController.class);

        Methods.updatePoints();
        pointslbl.setText("Points: " + Var.points);

        getPlanets(pref);
        checkPlanets();

        try {
            pref.exportNode(new FileOutputStream("Rewards.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public void updatePoints() {
        pref = Preferences.userNodeForPackage(RewardsController.class);
        pref.putInt("points", Var.points);
        try {
            pref.exportNode(new FileOutputStream("Rewards.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public void getPlanets(Preferences pref) {
        Var.mercury = pref.getBoolean("mercury", false);
        Var.venus = pref.getBoolean("venus", false);
        Var.earth = pref.getBoolean("earth", false);
        Var.mars = pref.getBoolean("mars", false);
        Var.jupiter = pref.getBoolean("jupiter", false);
        Var.saturn = pref.getBoolean("saturn", false);
        Var.uranus = pref.getBoolean("uranus", false);
        Var.neptune = pref.getBoolean("neptune", false);
    }

    public void updatePlanets() {
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

        checkPlanets();
    }

    public void checkPlanets() {
        if (Var.mercury) {
            mercury.setVisible(true);
        } if (Var.venus) {
            venus.setVisible(true);
        } if (Var.earth) {
            earth.setVisible(true);
        } if (Var.mars) {
            mars.setVisible(true);
        } if (Var.jupiter) {
            jupiter.setVisible(true);
        } if (Var.saturn) {
            saturn.setVisible(true);
        } if (Var.uranus) {
            uranus.setVisible(true);
        } if (Var.neptune) {
            neptune.setVisible(true);
        }
    }

}
