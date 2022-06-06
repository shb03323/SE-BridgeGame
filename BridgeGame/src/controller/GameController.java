package controller;

import validator.PlayerCanStayValidator;
import validator.PlayerInputValidator;
import view.MainFrame;
import view.RankDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    private PlayerController playerController;
    private MapController mapController;
    MainFrame gameView;

    // index of the player with the current turn
    private int turnNow;
    // rank of player
    private int rank = 1;

    // init game
    public void initGame() throws Exception {
        // init map
        mapController = new MapController();
        mapController.initMap();

        // init player
        playerController = new PlayerController();
        playerController.initPlayers();
        playerController.getInputPanel().setButtonListener(this);

        // set players in start cell
        mapController.initCharacter(playerController.getPlayerList().getPlayerListSize());

        // show GUI
        gameView = new MainFrame(playerController, mapController);
    }

    // add score when player get card
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
        playerController.getScoreBoardPanel().setScore(playerIndex, score);
    }

    // add bridge card number in player model
    private void addBridgeCardNum(int playerIndex, int num) {
        playerController.getPlayerList().getPlayer(playerIndex).obtainBridgeCard(num);
        playerController.getScoreBoardPanel().setBridgeCardNum(playerIndex, playerController.getPlayerList().getPlayer(playerIndex).getBridgeCardNum());
    }

    // player use bridge card
    private void useBridgeCard(int playerIndex) {
        playerController.getPlayerList().getPlayer(playerIndex).useBridgeCard();
        int bridgeCardNum = playerController.getPlayerList().getPlayer(playerIndex).getBridgeCardNum();
        playerController.getScoreBoardPanel().setBridgeCardNum(playerIndex, bridgeCardNum);
    }

    // get the score according to the current ranking
    private int getRankScore() {
        int score;
        switch (rank) {
            case 1 -> score = 7;
            case 2 -> score = 3;
            case 3 -> score = 1;
            default -> score = 0;
        }
        return score;
    }

    // when player get to end point, finished that player
    private void finishPlayer() {
        if (playerController.getPlayerList().getPlayer(turnNow).getCellNow() == mapController.getMap().getMapTileList().size() - 1) {
            playerController.getPlayerList().getPlayer(turnNow).addScore(getRankScore());
            playerController.getPlayerList().getPlayer(turnNow).setStatus(false);
            rank++;
        }
    }

    // give turn to next player
    private void changeTurn() {
        do {
            turnNow = (turnNow + 1) % playerController.getPlayerList().getPlayerListSize();
        } while (!playerController.getPlayerList().getPlayer(turnNow).getStatus());
        if (playerController.getPlayerList().getPlayerListSize() == rank) {
            new RankDialog(playerController.getPlayerList());
        }
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
            int bridgeCardNum = playerController.getPlayerList().getPlayer(turnNow).getBridgeCardNum();
            while (true) {
                String userInput = (String) JOptionPane.showInputDialog(null, "Dice number is " + diceNum + "\nBridge card number is " + bridgeCardNum, "Input Dialog", JOptionPane.PLAIN_MESSAGE, null, null, null);
                try {
                    int cellNow = playerController.getPlayerList().getPlayer(turnNow).getCellNow();
                    PlayerInputValidator playerInputValidator = new PlayerInputValidator(userInput, diceNum, bridgeCardNum, mapController.getMap(), cellNow);
                    if (playerInputValidator.validate()) {
                        playerController.getPlayerList().getPlayer(turnNow).setCellNow(playerInputValidator.getTileIndex());
                        // move player icon on map
                        mapController.setCharacter(playerInputValidator.getTileIndex(), turnNow);
                        addScore(turnNow, playerInputValidator.getTileIndex());
                        addBridgeCardNum(turnNow, playerInputValidator.getAddedBridgeCardNum());
                        // finish player
                        finishPlayer();
                        // change turn
                        changeTurn();
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
                    // use bridge card
                    useBridgeCard(turnNow);
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
