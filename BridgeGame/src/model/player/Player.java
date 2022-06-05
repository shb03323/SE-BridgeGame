package model.player;

public class Player {
    // player's name
    private final String name;

    // player's score
    private int score = 0;

    // number of bridge cards
    private int bridgeCardNum = 0;

    // index of the cell where the current player is
    private int cellNow = 0;

    /**
     * player's status. In game or end game
     * true : in game
     * false : finished game
     */
    private boolean status = true;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void obtainBridgeCard(int num) {
        bridgeCardNum += num;
    }

    public void useBridgeCard() {
        bridgeCardNum--;
    }

    public int getBridgeCardNum() {
        return bridgeCardNum;
    }

    public int getCellNow() {
        return cellNow;
    }

    public void setCellNow(int cellNow) {
        this.cellNow = cellNow;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
