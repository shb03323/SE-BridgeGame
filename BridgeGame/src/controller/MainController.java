package controller;

import jdk.jshell.spi.ExecutionControl;
import model.Player;
import model.card.HammerCard;

public class MainController {
    public static void main(String[] args) throws Exception {
        // TODO : init players
        Player player1 = new Player("minhyuk");
        player1.obtainCard(new HammerCard());

        System.out.println(player1.getScore());
    }
}
