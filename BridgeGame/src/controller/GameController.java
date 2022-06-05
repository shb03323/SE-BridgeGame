package controller;

import validator.PlayerCanStayValidator;
import validator.PlayerInputValidator;
import view.ImageLabel;
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
        playerController.getInputPanel().setButtonListener(this);

        // set players in start cell
        mapController.initCharacter(playerController.getPlayerList().getPlayerListSize());
        System.out.println(mapController.getPanel().getComponent(0).getBounds());
        System.out.println(mapController.getPanel().getComponent(64).getBounds());
        gameView = new MainFrame(playerController, mapController);
    }

    private void addScore(int playerIndex, int tileIndex) {
        int score;
        char tileName = mapController.getMap().getMapTileList().get(tileIndex).getTileName();
        switch (tileName) {
            case 'P' -> score = 1;
            case 'H' -> score = 2;
            case 'S' -> score = 3;
            default -> score = 0;
        }

        playerController.getPlayerList().getPlayer(playerIndex).addScore(score);
    }

    /**
     * Roll : show input text field and player can input the string in it.
     * Stay : player can stay and finish turn.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // when click Roll button, set up user input popup
        if (e.getSource() == playerController.getInputPanel().rollButton) {
            int diceNum = playerController.rollTheDice();
            while (true) {
                String userInput = (String) JOptionPane.showInputDialog(null, "Dice number is " + diceNum, "Input Dialog", JOptionPane.PLAIN_MESSAGE, null, null, null);
                try {
                    int cellNow = playerController.getPlayerList().getPlayer(turnNow).getCellNow();
                    PlayerInputValidator playerInputValidator = new PlayerInputValidator(userInput, diceNum, mapController.getMap(), cellNow);
                    if (playerInputValidator.validate()) {
                        playerController.getPlayerList().getPlayer(turnNow).setCellNow(playerInputValidator.getTileIndex());
                        // move player icon on map
                        mapController.setCharacter(playerInputValidator.getTileIndex(), turnNow);
                        addScore(turnNow, playerInputValidator.getTileIndex());
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
