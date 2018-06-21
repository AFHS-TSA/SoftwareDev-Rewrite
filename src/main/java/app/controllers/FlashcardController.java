package main.java.app.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.java.app.Var;
import org.Quizlet;

import java.net.URL;
import java.util.ResourceBundle;


public class FlashcardController implements Initializable {

    @FXML
    public JFXButton back;
    @FXML
    public JFXButton forward;
    @FXML
    public JFXButton star;
    @FXML
    public JFXButton flip;
    @FXML
    public Label card;
    @FXML
    public Label title;

    int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*String[] arr = {"-dict-", "to say", "0"};
        String[] arr2 = {"osteo", "bone", "0"};
        Var.flashSets.add(arr);
        Var.flashSets.add(arr2);*/
        try {
            Quizlet.setQuiz(Var.clientID, Var.flashID);
            String def[] = Quizlet.getDefinition();
            String term[] = Quizlet.getTerms();
            for(int i=0;i<Quizlet.getTermCount();i++) {
                String[] card = {term[i], def[i], "0"};
                Var.flashSets.add(card);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        card.setText(Var.flashSets.get(0)[0]);
        if (Var.flashSets.get(index)[2].equals("0")) {
            star.setTextFill(Color.WHITE);
        } else {
            star.setTextFill(Color.YELLOW);
        }
    }

    public void onFlip() {
        if (Var.flashSets.get(index)[0].equals(card.getText())) {
            card.setText(Var.flashSets.get(index)[1]);
            card.setFont(Font.font(12));
        } else {
            card.setText(Var.flashSets.get(index)[0]);
            card.setFont(Font.font(20));
        }
    }

    public void onFor() {
        index++;
        if (index > (Var.flashSets.size() - 1)) {
            index = 0;
        }
        if (Var.flashSets.get(index)[2].equals("0")) {
            star.setTextFill(Color.WHITE);
        } else {
            star.setTextFill(Color.YELLOW);
        }
        card.setText((Var.flashSets.get(index)[0]));
        card.setFont(Font.font(20));
    }

    public void onBack() {
        index--;
        if (index < 0) {
            index = Var.flashSets.size() - 1;
        }
        if (Var.flashSets.get(index)[2].equals("0")) {
            star.setTextFill(Color.WHITE);
        } else {
            star.setTextFill(Color.YELLOW);
        }

        card.setText((Var.flashSets.get(index)[0]));
        card.setFont(Font.font(20));
    }

    public void onStar() {
        if (Var.flashSets.get(index)[2].equals("0")) {
            Var.flashSets.get(index)[2] = "1";
            star.setTextFill(Color.YELLOW);
        } else {
            Var.flashSets.get(index)[2] = "0";
            star.setTextFill(Color.WHITE);
        }
    }
}

