package model;

import java.util.ArrayList;

public class PlayerList {
    /**
     * player list who are playing a game.
     * Except player who finished a game.
     */
    private ArrayList<Player> playerList;

    // turn of player
    private int turn = 0;

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public Player getNextPlayer() {
        int playerIndex = turn;
        turn = playerIndex++ % playerList.size();
        return playerList.get(playerIndex);
    }

    public void removePlayer() {
        playerList.remove(turn);
    }
}
