package view;

import view.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerScoreBoardPanel extends JPanel {
    private final ArrayList<JLabel> playerScoreList = new ArrayList<>();
    private final ArrayList<JLabel> bridgeCardList = new ArrayList<>();

    // Set players score board, must input player number.
    public PlayerScoreBoardPanel(int playerNum, ArrayList<String> playerNames) {
        this.setPreferredSize(new Dimension(100, 1000));
        this.setLayout(new GridLayout(playerNum, 0));

        for (int i = 0; i < playerNum; i++) {
            playerScoreList.add(new JLabel("Score : 0"));
            bridgeCardList.add(new JLabel("Bridge card : 0"));
            setSinglePlayerInfoBoard(i, playerNames.get(i));
        }
    }

    // set player information on board
    private void setSinglePlayerInfoBoard(int i, String name) {
        JPanel panel = new JPanel(new GridLayout(4, 0));
        Util.changePanelWithBorder(panel);

        JLabel playerName = initPlayerNameLabel(i, name);
        JLabel playerIcon = initPlayerIconLabel(i);
        JLabel playerScore = initPlayerScoreLabel(i);
        JLabel bridgeCard = initBridgeCard(i);
        panel.add(playerName);
        panel.add(playerIcon);
        panel.add(playerScore);
        panel.add(bridgeCard);
        this.add(panel);
    }

    // set player information
    private JLabel initPlayerNameLabel(int i, String name) {
        JLabel nameLabel = new JLabel(name);
        Util.setTextSize(nameLabel, 20);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        return nameLabel;
    }

    private JLabel initPlayerIconLabel(int i) {
        String currentWorkingDirectory = System.getProperty("user.dir");
        ImageIcon imageIcon = new ImageIcon(currentWorkingDirectory + "/src/assets/tile/Player" + (i + 1) + ".png");
        imageIcon = Util.setIconSize(imageIcon, 40);
        return new JLabel(imageIcon);
    }

    private JLabel initPlayerScoreLabel(int i) {
        JLabel scoreLabel = playerScoreList.get(i);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        return scoreLabel;
    }

    private JLabel initBridgeCard(int i) {
        JLabel bridgeNumberLabel = bridgeCardList.get(i);
        bridgeNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        return bridgeNumberLabel;
    }

    // set score of player
    public void setScore(int i, String score) {
        playerScoreList.get(i).setText(score);
    }

    // set bridge card number of player
    public void setBridgeCardNum(int i, int num) {
        bridgeCardList.get(i).setText("Bridge card : " + num);
    }
}
