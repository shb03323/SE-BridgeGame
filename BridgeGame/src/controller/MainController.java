package controller;

import model.Player;
import model.card.HammerCard;
import model.BridgeMap;

public class MainController {
    public static void main(String[] args) throws Exception {
        // TODO : init players
        Player player1 = new Player("minhyuk");
        player1.obtainCard(new HammerCard());
        BridgeMap bridgeMap = new BridgeMap();
        System.out.println(bridgeMap.bridgeMap);

        System.out.println(player1.getScore());
    }
}
