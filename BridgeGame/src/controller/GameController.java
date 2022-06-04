package controller;

import validator.PlayerNumberValidator;
import view.MainFrame;
import view.PlayerScoreBoardPanel;

import javax.swing.*;
import java.awt.*;

public class GameController extends Component {
    private PlayerController playerController;
    private MapController mapController;
    MainFrame gameView;

    public void initGame() throws Exception {
        mapController = new MapController();
        mapController.initMap();

        playerController = new PlayerController(mapController.getMap());
        playerController.initPlayers();

        gameView = new MainFrame(playerController, mapController);
    }

    public void startGame() throws Exception {
        
    }
}
