package controller;

import model.player.Player;
import model.player.PlayerList;
import validator.PlayerCanStayValidator;
import validator.PlayerNumberValidator;
import view.InputObserver;
import view.InputPanel;
import view.PlayerScoreBoardObserver;
import view.PlayerScoreBoardPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class PlayerController implements ActionListener {
    // player list for game
    private PlayerList playerList;

    private PlayerScoreBoardObserver playerScoreBoardObserver;
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
        playerScoreBoardObserver = new PlayerScoreBoardPanel(playerNumber, playerNames);

        inputPanel = new InputPanel();
        inputPanel.setButtonListener(this);
    }

    public PlayerScoreBoardObserver getPanel() {
        return playerScoreBoardObserver;
    }

    /**
     * Player can choose action when they get turn.
     * return value is 0 ~ 6
     * 0 : stay
     * 1 ~ 6 : movement
     */
    public int chooseAction() throws Exception {
        // TODO : GUI에서 행동을 선택하도록 함
        boolean flag = true;
        // cannot move when (dice value - bridge card number of player) is under 0
        if (flag) {
            int moveCount = rollTheDice() - playerList.getPlayer().getBridgeCardNum();
            return Math.max(moveCount, 0);
        } else {
            while (true) {
                if (new PlayerCanStayValidator(playerList.getPlayer()).validate()) {
                    stay();
                    return 0;
                } else {
                    alertCannotStay();
                }
            }
        }
    }

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
        int diceValue = random.nextInt(1, 7);
        // TODO : show dice number in GUI
        return diceValue;
    }

    // input player movement
    public void inputMovement(int num) {

    }

    private void stay() {

    }

    private void alertCannotStay() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inputPanel.moveButton) {

        }
    }
}
