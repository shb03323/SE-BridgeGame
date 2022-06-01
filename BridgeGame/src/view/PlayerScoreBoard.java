package view;

import view.utils.Util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PlayerScoreBoard extends JPanel {
    public PlayerScoreBoard(int playerNum) {
        this.setLayout(new GridLayout(playerNum, 0));

        for (int i = 0; i < playerNum; i++) {
            setSinglePlayerInfoBoard(i);
        }
    }

    // set player information on board
    private void setSinglePlayerInfoBoard(int i) {
        JPanel panel = new JPanel(new GridLayout(3, 3));
        Util.changePanelWithBorder(panel);

        String currentWorkingDirectory = System.getProperty("user.dir");
        JLabel playerName = getPlayerNameLabel(i);
        JLabel playerIcon = new JLabel();
        playerIcon.setIcon(new ImageIcon(currentWorkingDirectory + "/src/assets/tile/Player" + (i + 1) + ".png"));
        playerIcon.setSize(12, 12);
        panel.add(playerName);
        panel.add(playerIcon);
        this.add(panel);
    }

    private JLabel getPlayerNameLabel(int i) {
        JLabel playerName = new JLabel("Player " + (i + 1));

        Font font = new Font("Arial", Font.BOLD, 20);
        playerName.setFont(font);
        return playerName;
    }
}
