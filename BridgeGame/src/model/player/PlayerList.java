package model.player;

import java.util.ArrayList;

public class PlayerList {
    /**
     * player list who are playing a game.
     * Except player who finished a game.
     */
    private ArrayList<Player> playerList;

    // turn of player
    private int turn = -1;

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public Player getNextPlayer() {
        turn = ++turn % playerList.size();
        return playerList.get(turn);
    }
}
