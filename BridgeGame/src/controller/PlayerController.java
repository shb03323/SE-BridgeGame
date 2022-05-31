package controller;

import model.player.Player;
import model.player.PlayerList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerController {
    public void initPlayers(int playerNum) throws IOException {
        PlayerList playerList = new PlayerList();
        for (int i = 0; i < playerNum; i++) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            playerList.addPlayer(new Player(reader.readLine()));
        }
    }
}
