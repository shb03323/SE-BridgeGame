package view;

import view.utils.Util;

import javax.swing.*;
import java.awt.*;

public class PlayerScoreBoardPanel extends JPanel {
    // Set players score board, must input player number.
    public PlayerScoreBoardPanel(int playerNum) {
        this.setLayout(new GridLayout(playerNum, 0));

        for (int i = 0; i < playerNum; i++) {
            setSinglePlayerInfoBoard(i);
        }
    }

    // set player information on board
    private void setSinglePlayerInfoBoard(int i) {
        JPanel panel = new JPanel(new GridLayout(4, 0));
        Util.changePanelWithBorder(panel);

        JLabel playerName = getPlayerNameLabel(i);
        JLabel playerIcon = getPlayerIconLabel(i);
        JLabel playerScore = getPlayerScoreLabel(i);
        panel.add(playerName);
        panel.add(playerIcon);
        panel.add(playerScore);
        this.add(panel);
    }

    private JLabel getPlayerNameLabel(int i) {
        JLabel playerName = new JLabel("Player " + (i + 1));
        Util.setTextSize(playerName, 20);
        playerName.setHorizontalAlignment(JLabel.CENTER);
        return playerName;
    }

    private JLabel getPlayerIconLabel(int i) {
        String currentWorkingDirectory = System.getProperty("user.dir");
        ImageIcon imageIcon = new ImageIcon(currentWorkingDirectory + "/src/assets/tile/Player" + (i + 1) + ".png");
        imageIcon = Util.setIconSize(imageIcon, 40);
        return new JLabel(imageIcon);
    }

    private JLabel getPlayerScoreLabel(int i) {
        JLabel playerScore = new JLabel("Score : " + 12);
        playerScore.setHorizontalAlignment(JLabel.CENTER);
        return playerScore;
    }
}
