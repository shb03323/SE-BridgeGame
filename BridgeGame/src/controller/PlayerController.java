package controller;

import model.BridgeMap;
import model.player.Player;
import model.player.PlayerList;
import validator.PlayerCanStayValidator;
import validator.PlayerInputValidator;
import validator.PlayerNumberValidator;
import view.InputPanel;
import view.PlayerScoreBoardPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class PlayerController implements ActionListener {
    // player list for game
    private PlayerList playerList;

    // map for game
    private BridgeMap bridgeMap;

    // index of the player with the current turn
    private int turnNow;

    // views
    private PlayerScoreBoardPanel playerScoreBoardPanel;
    private InputPanel inputPanel;

    public PlayerController(BridgeMap bridgeMap) {
        this.bridgeMap = bridgeMap;
    }

    // init players
    public void initPlayers() throws Exception {
        playerList = new PlayerList();

        int playerNumber = getPlayerNumberByUserInput();
        setPlayerListByUserInput(playerNumber);
        ArrayList<String> playerNames = new ArrayList<>();
        for (int i = 0; i < playerNumber; i++) {
            playerNames.add(playerList.getPlayer(i).getName());
        }
        playerScoreBoardPanel = new PlayerScoreBoardPanel(playerNumber, playerNames);

        inputPanel = new InputPanel();
        inputPanel.setPlayerName(playerList.getPlayer(0).getName());
        inputPanel.setButtonListener(this);
    }

    public PlayerScoreBoardPanel getScoreBoardPanel() {
        return playerScoreBoardPanel;
    }

    public InputPanel getInputPanel() { return inputPanel; }

    // get player number and each player name by GUI input
    private int getPlayerNumberByUserInput() throws Exception {
        int result;
        while (true) {
            String playerNumber = (String) JOptionPane.showInputDialog(null, "Input player number 2 ~ 4", null, JOptionPane.PLAIN_MESSAGE, null, null, null);
            PlayerNumberValidator playerNumberValidator = new PlayerNumberValidator(playerNumber);
            if (playerNumberValidator.validate()) {
                result = Integer.parseInt(playerNumber);
                break;
            }
        }

        return result;
    }

    // set each player's name
    private void setPlayerListByUserInput(int playerNum) {
        for (int i = 1; i <= playerNum; i++) {
            String playerName = (String) JOptionPane.showInputDialog(null, "Input player " + i + "'s name", null, JOptionPane.PLAIN_MESSAGE, null, null, null);
            playerList.addPlayer(new Player(playerName));
        }
    }

    // system roll the dice
    private int rollTheDice() {
        Random random = new Random();
        return random.nextInt(1, 7);
    }

    /**
     * Roll : show input text field and player can input the string in it.
     * Stay : player can stay and finish turn.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // when click Roll button, set up user input popup
        if (e.getSource() == inputPanel.rollButton) {
            while (true) {
                int diceNum = rollTheDice();
                String userInput = (String) JOptionPane.showInputDialog(null, "Dice number is " + diceNum, "Input Dialog", JOptionPane.PLAIN_MESSAGE, null, null, null);
                try {
                    PlayerInputValidator playerInputValidator = new PlayerInputValidator(userInput, diceNum, bridgeMap, turnNow);
                    if (playerInputValidator.validate()) {
                        playerList.getPlayer(turnNow).setCellNow(playerInputValidator.getTileIndex());
                        // finish turn
                        turnNow = (turnNow + 1) % playerList.getPlayerListSize();
                        // change the text of remark label
                        inputPanel.setPlayerName(playerList.getPlayer(turnNow).getName());
                        break;
                    }
                    // when user input wrong, show error dialog
                    JOptionPane.showMessageDialog(null, "Wrong Input");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource() == inputPanel.stayButton) { // stay button click event
            try {
                if (new PlayerCanStayValidator(playerList.getPlayer(turnNow)).validate()) {
                    // finish turn
                    turnNow = (turnNow + 1) % playerList.getPlayerListSize();
                    // change the text of remark label
                    inputPanel.setPlayerName(playerList.getPlayer(turnNow).getName());
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have bridge card.");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
