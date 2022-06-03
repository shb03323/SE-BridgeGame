package model.player;

import java.util.ArrayList;

public class PlayerList {
    /**
     * player list who are playing a game.
     * Except player who finished a game.
     */
    private ArrayList<Player> playerList = new ArrayList<>();

    // turn of player
    private int turn = -1;

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public Player getNextPlayer() {
        turn = ++turn % playerList.size();
        return playerList.get(turn);
    }

    public Player getPlayer() {
        return playerList.get(turn);
    }

    public Player getPlayer(int i) {
        return playerList.get(i);
    }
}
