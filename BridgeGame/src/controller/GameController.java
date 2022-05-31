package controller;

import view.MainFrame;

public class GameController {
    private final PlayerController playerController = new PlayerController();
    private final MapController mapController = new MapController();
    MainFrame gameView = new MainFrame();

    public void initGame() throws Exception {
        // TODO : set player number by GUI
        int playerNum = 4;
        playerController.initPlayers(playerNum);
        mapController.initMap();
    }

    public void takeTurn() {

    }
}
