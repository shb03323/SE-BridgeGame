package model;

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

    // cards which player is holding
    private ArrayList<Card> cardList = new ArrayList();

    public Player(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void obtainCard(Card card) {
        this.cardList.add(card);
    }

    public boolean useCard(Card card) {
        // TODO : add logic of card using
    }
}
