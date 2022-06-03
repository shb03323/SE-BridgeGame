package view;

import controller.PlayerController;
import view.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerScoreBoardPanel extends JPanel implements Observer {
    private int playerNum;
    private ArrayList<JLabel> playerNameList;
    private ArrayList<JLabel> playerScoreList;

    // Set players score board, must input player number.
    public PlayerScoreBoardPanel() {
        initPlayerNumber(playerNum);
        this.setLayout(new GridLayout(playerNum, 0));

        for (int i = 0; i < playerNum; i++) {
            playerNameList.add(new JLabel("player"));
            setSinglePlayerInfoBoard(i);
        }
    }

    // set player information on board
    private void setSinglePlayerInfoBoard(int i) {
        JPanel panel = new JPanel(new GridLayout(4, 0));
        Util.changePanelWithBorder(panel);

        JLabel playerName = playerController.setPlayerNameLabel(i);
        JLabel playerIcon = playerController.setPlayerIconLabel(i);
        JLabel playerScore = playerController.setPlayerScoreLabel(i);
        panel.add(playerName);
        panel.add(playerIcon);
        panel.add(playerScore);
        this.add(panel);
    }

    public void setPlayerScoreLabel(int playerIndex, int score) {

    }

    // set player information
    public JLabel setPlayerNameLabel(int i) {
        playerNameList.get(i).setText(pl);
        playerName.setText(playerName);
        Util.setTextSize(playerName, 20);
        playerName.setHorizontalAlignment(JLabel.CENTER);
        return playerName;
    }

    public JLabel setPlayerIconLabel(int i) {
        String currentWorkingDirectory = System.getProperty("user.dir");
        ImageIcon imageIcon = new ImageIcon(currentWorkingDirectory + "/src/assets/tile/Player" + (i + 1) + ".png");
        imageIcon = Util.setIconSize(imageIcon, 40);
        return new JLabel(imageIcon);
    }

    public JLabel setPlayerScoreLabel(int i) {
        JLabel playerScore = new JLabel("Score : " + playerList.getPlayer(i).getScore());
        playerScore.setHorizontalAlignment(JLabel.CENTER);
        return playerScore;
    }

    public void setPlayerName(String name) {
        playerName = name;
    }

    @Override
    public void initPlayerNumber(int playerNumber) {
        playerNum = playerNumber;
    }

    @Override
    public void initPlayerName(String name) {
        setPlayerName(name);
    }
}
