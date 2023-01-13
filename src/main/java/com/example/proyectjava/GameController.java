package com.example.proyectjava;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class GameController {
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
    @FXML
    private Button reset;
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
            buttons.forEach(button -> {
                compSelctTurn();
                button.setFocusTraversable(false);
            });
        }
    }
    //Reseteara el juego
    @FXML
    void restartGame(){
        buttons.forEach(this::resetButton);
    }

//volver el boton al valor inicial
    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");

    }

//Identificar los clicks en PlayervsPlayer
    public void setupButton(Button button){
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }
    //Decidir los turnos en PlayervsPlayer
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

    public void compSetButton(String resultado) {
        boolean posiblePintarCasilla = false;
        while (!posiblePintarCasilla) {
            int resultadoComp = (int) Math.floor(Math.random() * 9);
            switch (resultadoComp) {
                case 0:
                    if(button1.isDisabled()){
                        break;
                    }
                    else {
                        button1.setText(resultado);
                        button1.setDisable(true);
                        checkIfGameIsOver();
                        posiblePintarCasilla=true;
                        break;
                    }
                case 1:
                    button2.setText(resultado);
                    button2.setDisable(true);
                    checkIfGameIsOver();
                    break;
                case 2:
                    button3.setText(resultado);
                    button3.setDisable(true);
                    checkIfGameIsOver();
                    break;
                case 3:
                    button4.setText(resultado);
                    button4.setDisable(true);
                    checkIfGameIsOver();
                    break;
                case 4:
                    button5.setText(resultado);
                    button5.setDisable(true);
                    checkIfGameIsOver();
                    break;
                case 5:
                    button6.setText(resultado);
                    button6.setDisable(true);
                    checkIfGameIsOver();
                    break;
                case 6:
                    button7.setText(resultado);
                    button7.setDisable(true);
                    checkIfGameIsOver();
                    break;
                case 7:
                    button8.setText(resultado);
                    button8.setDisable(true);
                    checkIfGameIsOver();
                    break;
                case 8:
                    button9.setText(resultado);
                    button9.setDisable(true);
                    checkIfGameIsOver();
                    break;
            }
        }
    }
    public void compSelctTurn() {
        if(playerTurnNumber % 2==0){
            String resultado="X";
            compSetButton(resultado);
            playerTurnNumber = 1;
            playerTurnText.setText("Playing: Player 2");
        }
        else {
            String resultado="O";
            compSetButton(resultado);
            playerTurnNumber=0;
            playerTurnText.setText("Playing: Player 1");
        }
    }
//Comprueba si alguien ha ganado
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
    //Cierra el juego
    @FXML
    protected void closeGame() {
        System.exit(0);
    }
}