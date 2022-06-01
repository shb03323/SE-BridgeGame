package controller;

import model.player.Player;
import model.player.PlayerList;
import validator.PlayerCanStayValidator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

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

    public void chooseAction() throws Exception {
        // TODO : GUI에서 행동을 선택하도록 함
        boolean flag = true;
        if (flag) {
            int diceNum = rollTheDice();
            chooseMovement(diceNum);
        } else {
            if (new PlayerCanStayValidator(playerList.getPlayer()).validate()) {
                stay();
            } else {
                alertCannotStay();
            }
        }
    }

    private int rollTheDice() {
        Random random = new Random();
        return random.nextInt(1, 7);
    }

    private void chooseMovement(int num) {

    }

    private void stay() {

    }

    private void alertCannotStay() {

    }
}
