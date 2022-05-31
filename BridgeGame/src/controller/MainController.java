package controller;

import model.Player;
import model.Position;
import model.card.HammerCard;
import model.BridgeMap;

public class MainController {
    public static void main(String[] args) throws Exception {
        // TODO : init players
        Player player1 = new Player("minhyuk");
        player1.obtainCard(new HammerCard());
        System.out.println(player1.getScore());

        // initiation map
        BridgeMap bridgeMap = FileController.initMap();
        System.out.println(bridgeMap.getHeight());
        System.out.println(bridgeMap.getWidth());
    }
}
