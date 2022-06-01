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

    // init players
    public void initPlayers(int playerNum) throws Exception {
        // TODO : 콘솔 말고 GUI로 받기
        PlayerList playerList = new PlayerList();
        for (int i = 0; i < playerNum; i++) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            playerList.addPlayer(new Player(reader.readLine()));
        }
    }

    /**
     * Player can choose action when they get turn.
     * return value is 0 ~ 6
     * 0 : stay
     * 1 ~ 6 : movement
     */
    public int chooseAction() throws Exception {
        // TODO : GUI에서 행동을 선택하도록 함
        boolean flag = true;
        // cannot move when (dice value - bridge card number of player) is under 0
        if (flag) {
            int moveCount = rollTheDice() - playerList.getPlayer().getBridgeCardNum();
            return Math.max(moveCount, 0);
        } else {
            while (true) {
                if (new PlayerCanStayValidator(playerList.getPlayer()).validate()) {
                    stay();
                    return 0;
                } else {
                    alertCannotStay();
                }
            }
        }
    }

    // system roll the dice
    private int rollTheDice() {
        Random random = new Random();
        int diceValue = random.nextInt(1, 7);
        // TODO : show dice number in GUI
        return diceValue;
    }

    // input player movement
    public void inputMovement(int num) {

    }

    private void stay() {

    }

    private void alertCannotStay() {

    }
}
