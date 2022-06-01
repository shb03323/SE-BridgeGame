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


        JLabel playerName = getPlayerNameLabel(i);
        JLabel playerIcon = getPlayerIconLabel(i);
        panel.add(playerName);
        panel.add(playerIcon);
        this.add(panel);
    }

    private JLabel getPlayerNameLabel(int i) {
        JLabel playerName = new JLabel("Player " + (i + 1));
        Util.setBigText(playerName);
        playerName.setHorizontalAlignment(JLabel.CENTER);
        return playerName;
    }

    private JLabel getPlayerIconLabel(int i) {
        String currentWorkingDirectory = System.getProperty("user.dir");
        ImageIcon imageIcon = new ImageIcon(currentWorkingDirectory + "/src/assets/tile/Player" + (i + 1) + ".png");
        imageIcon = Util.setIconSize(imageIcon, 40);
        return new JLabel(imageIcon);
    }
}
