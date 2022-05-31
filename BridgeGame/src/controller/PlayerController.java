package controller;

import model.Player;
import model.PlayerList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PlayerController {
    public void initPlayers(int playerNum) throws IOException {
        PlayerList playerList = new PlayerList();
        for (int i = 0; i < playerNum; i++) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            playerList.addPlayer(new Player(reader.readLine()));
        }
    }
}
