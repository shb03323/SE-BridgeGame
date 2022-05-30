package model;

import jdk.jshell.spi.ExecutionControl;
import model.card.Card;

import java.util.ArrayList;

public class Player {
    // player's name
    private String name;

    // player's score
    private int score = 0;

    /**
     * player's status. In game or end game
     * true : in game
     * false : finished game
     */
    private boolean status = true;

    public Player(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void obtainCard(Card card) throws Exception {
        switch (card.getClass().getSimpleName()) {
            case "PhilipsDriverCard" -> score += 1;
            case "HammerCard" -> score += 2;
            case "SawCard" -> score += 3;
            default -> throw new Exception("There are no such cards in this game.");
        }
    }
}
