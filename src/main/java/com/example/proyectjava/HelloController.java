package com.example.proyectjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HelloController  {
    @FXML
    private Label welcomeText;

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    private int playerTurnNumber = 0;
    @FXML
    private Text playerTurnText;

    @FXML
    private RadioButton CompVsComp;
    @FXML
    private RadioButton PlayerVsComp;

    @FXML
    private RadioButton PlayerVsPlayer;


    ArrayList<Button> buttons;

    public void initialize(){
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));

        if(PlayerVsPlayer.isSelected()){
            buttons.forEach(button -> {
                setupButton(button);
                button.setFocusTraversable(false);
            });
        }
        else if(CompVsComp.isSelected()){

        }
    }
    @FXML
    void restartGame(){
        buttons.forEach(this::resetButton);
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");

    }


    public void setupButton(Button button){
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button){
        if(playerTurnNumber % 2==0){
            button.setText("X");
            playerTurnNumber = 1;
            playerTurnText.setText("Playing: Player 2");
        }
        else {
            button.setText("O");
            playerTurnNumber=0;
            playerTurnText.setText("Playing: Player 1");
        }
    }

    public void checkIfGameIsOver() {
        for (int i = 0; i < 8; i++) {
            String line = switch (i) {
              case 0 -> button1.getText() + button2.getText() + button3.getText();
              case 1 -> button4.getText() + button5.getText() + button6.getText();
              case 2 -> button7.getText() + button8.getText() + button9.getText();
              case 3 -> button1.getText() + button5.getText() + button9.getText();
              case 4 -> button3.getText() + button5.getText() + button7.getText();
              case 5 -> button1.getText() + button4.getText() + button7.getText();
              case 6 -> button2.getText() + button5.getText() + button8.getText();
              case 7 -> button3.getText() + button6.getText() + button9.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")){
                playerTurnText.setText("Player 1 won!");
                restartGame();
            }
            //O winner
            else if (line.equals("OOO")) {
                playerTurnText.setText("Player 2 won!");
                restartGame();
            }
        }
    }
    @FXML
    protected void closeGame() {
        System.exit(0);
    }
}