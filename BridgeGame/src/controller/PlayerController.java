package controller;

import model.player.Player;
import model.player.PlayerList;
import validator.PlayerNumberValidator;
import view.InputPanel;
import view.PlayerScoreBoardPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayerController {
    // player list for game
    private PlayerList playerList;

    // views
    private PlayerScoreBoardPanel playerScoreBoardPanel;
    private InputPanel inputPanel;

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
    }

    public PlayerList getPlayerList() {
        return playerList;
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
    public int rollTheDice() {
        Random random = new Random();
        return random.nextInt(1, 7);
    }
}
