package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Bridge Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPanel();

        setWindow();
    }

    private void setWindow() {
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setPanel() {
        Container container = this.getContentPane();

        // TODO : change playerNum to user input
        int playerNum = 4;

        // set border layout
        JPanel panel = new JPanel(new BorderLayout(100, 100));

        panel.add(new PlayerScoreBoard(playerNum), BorderLayout.WEST);

        container.add(panel);
    }
}
