package controller;

import model.player.Player;
import model.card.HammerCard;
import model.BridgeMap;
import view.MainFrame;

public class MainController {
    public static void main(String[] args) throws Exception {
        MainFrame gameView = new MainFrame();

        // TODO : init players
        Player player1 = new Player("minhyuk");
        player1.obtainCard(new HammerCard());
        System.out.println(player1.getScore());

        // initiation map
        BridgeMap bridgeMap = MapController.initMap();
    }
}
