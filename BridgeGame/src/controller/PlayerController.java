package controller;

import model.player.Player;
import model.player.PlayerList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerController {
    // player list for game
    private PlayerList playerList;

    public void initPlayers(int playerNum) throws Exception {
        PlayerList playerList = new PlayerList();
        for (int i = 0; i < playerNum; i++) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            playerList.addPlayer(new Player(reader.readLine()));
        }
    }

    public void chooseAction() {
        // TODO : GUI에서 행동을 선택하도록 함
        boolean flag = true;
        if (flag) {
            rollTheDice();
        } else {
            if () {
                // TODO : stay할 수 있는 지 확인
            } else {
                rollTheDice();
            }
        }
    }

    private void rollTheDice() {

    }

    private void stay() {

    }
}
