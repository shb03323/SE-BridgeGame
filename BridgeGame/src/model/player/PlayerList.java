package model.player;

import java.util.ArrayList;

public class PlayerList {
    private ArrayList<Player> playerList = new ArrayList<>();

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public Player getPlayer(int i) {
        return playerList.get(i);
    }

    public int getPlayerListSize() {
        return playerList.size();
    }
}
