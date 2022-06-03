package controller;

import validator.PlayerNumberValidator;
import view.MainFrame;
import view.PlayerScoreBoardPanel;

import javax.swing.*;
import java.awt.*;

public class GameController extends Component {
    private final PlayerController playerController = new PlayerController();
    private final MapController mapController = new MapController();
    MainFrame gameView;

    public void initGame() throws Exception {
        playerController.initPlayers();
        mapController.initMap();
        gameView = new MainFrame(playerController, mapController);
    }

    public void takeTurn() throws Exception {
        int moveCount = playerController.chooseAction();
        if (moveCount > 0) {
            playerController.inputMovement(moveCount);
        }
    }
}
