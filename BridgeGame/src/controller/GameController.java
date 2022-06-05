package controller;

import validator.PlayerCanStayValidator;
import validator.PlayerInputValidator;
import validator.PlayerNumberValidator;
import view.MainFrame;
import view.PlayerScoreBoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    private PlayerController playerController;
    private MapController mapController;
    MainFrame gameView;

    // index of the player with the current turn
    private int turnNow;

    public void initGame() throws Exception {
        mapController = new MapController();
        mapController.initMap();

        playerController = new PlayerController();
        playerController.initPlayers();

        gameView = new MainFrame(playerController, mapController);
    }

    public void startGame() throws Exception {
    }

    /**
     * Roll : show input text field and player can input the string in it.
     * Stay : player can stay and finish turn.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // when click Roll button, set up user input popup
        if (e.getSource() == playerController.getInputPanel().rollButton) {
            while (true) {
                int diceNum = playerController.rollTheDice();
                String userInput = (String) JOptionPane.showInputDialog(null, "Dice number is " + diceNum, "Input Dialog", JOptionPane.PLAIN_MESSAGE, null, null, null);
                try {
                    PlayerInputValidator playerInputValidator = new PlayerInputValidator(userInput, diceNum, mapController.getMap(), turnNow);
                    if (playerInputValidator.validate()) {
                        playerController.getPlayerList().getPlayer(turnNow).setCellNow(playerInputValidator.getTileIndex());
                        // finish turn
                        turnNow = (turnNow + 1) % playerController.getPlayerList().getPlayerListSize();
                        // change the text of remark label
                        playerController.getInputPanel().setPlayerName(playerController.getPlayerList().getPlayer(turnNow).getName());
                        break;
                    }
                    // when user input wrong, show error dialog
                    JOptionPane.showMessageDialog(null, "Wrong Input");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource() == playerController.getInputPanel().stayButton) { // stay button click event
            try {
                if (new PlayerCanStayValidator(playerController.getPlayerList().getPlayer(turnNow)).validate()) {
                    // finish turn
                    turnNow = (turnNow + 1) % playerController.getPlayerList().getPlayerListSize();
                    // change the text of remark label
                    playerController.getInputPanel().setPlayerName(playerController.getPlayerList().getPlayer(turnNow).getName());
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have bridge card.");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
