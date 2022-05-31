package view;

import javax.swing.*;
import java.awt.*;

public class PlayerScoreBoard extends JPanel {
    public PlayerScoreBoard(int playerNum) {
        this.setLayout(new GridLayout(playerNum, 1));

        for (int i = 0; i < playerNum; i++) {
            this.add(new JButton());
        }
    }

    private void setSinglePlayerInfo() {

    }
}
