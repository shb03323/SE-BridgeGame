package controller;

import validator.PlayerNumberValidator;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class GameController extends Component {
    private final PlayerController playerController = new PlayerController();
    private final MapController mapController = new MapController();
    MainFrame gameView = new MainFrame();

    public void initGame() throws Exception {
        int playerNum = getPlayerNumberByUserInput();
        playerController.initPlayers(playerNum);
        mapController.initMap();
    }

    public void takeTurn() throws Exception {
        int moveCount = playerController.chooseAction();
        if (moveCount > 0) {
            playerController.inputMovement(moveCount);
        }
    }

    // get player number and each player name by GUI input
    private int getPlayerNumberByUserInput() throws Exception {
        int result;
        while (true) {
            String playerNumber = (String) JOptionPane.showInputDialog(this, "Input player number 2 ~ 4", null, JOptionPane.PLAIN_MESSAGE, null, null, null);
            PlayerNumberValidator playerNumberValidator = new PlayerNumberValidator(playerNumber);
            if (playerNumberValidator.validate()) {
                result = Integer.parseInt(playerNumber);
                break;
            }
        }

        return result;
    }
}
