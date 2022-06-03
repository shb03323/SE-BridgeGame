package view;

import view.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerScoreBoardPanel extends JPanel implements PlayerScoreBoardObserver {
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

        JLabel playerName = setPlayerNameLabel(i, name);
        JLabel playerIcon = setPlayerIconLabel(i);
        JLabel playerScore = setPlayerScoreLabel(i);
        JLabel bridgeCard = setBridgeCard(i);
        panel.add(playerName);
        panel.add(playerIcon);
        panel.add(playerScore);
        panel.add(bridgeCard);
        this.add(panel);
    }

    // set player information
    private JLabel setPlayerNameLabel(int i, String name) {
        JLabel nameLabel = new JLabel(name);
        Util.setTextSize(nameLabel, 20);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        return nameLabel;
    }

    private JLabel setPlayerIconLabel(int i) {
        String currentWorkingDirectory = System.getProperty("user.dir");
        ImageIcon imageIcon = new ImageIcon(currentWorkingDirectory + "/src/assets/tile/Player" + (i + 1) + ".png");
        imageIcon = Util.setIconSize(imageIcon, 40);
        return new JLabel(imageIcon);
    }

    public JLabel setPlayerScoreLabel(int i) {
        JLabel scoreLabel = playerScoreList.get(i);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        return scoreLabel;
    }

    public JLabel setBridgeCard(int i) {
        JLabel bridgeNumberLabel = bridgeCardList.get(i);
        bridgeNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        return bridgeNumberLabel;
    }

    @Override
    public void setScore(int i, String score) {
        playerScoreList.get(i).setText(score);
    }

    @Override
    public void setBridgeCardNum(int i, String bridgeCardNum) {
        bridgeCardList.get(i).setText(bridgeCardNum);
    }
}
