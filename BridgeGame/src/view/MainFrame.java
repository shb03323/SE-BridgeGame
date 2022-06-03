package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Bridge Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initPanel();

        setWindow();
    }

    private void setWindow() {
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initPanel() {
        Container container = this.getContentPane();

        // set border layout
        JPanel panel = new JPanel(new BorderLayout(100, 100));

        panel.add(new PlayerScoreBoardPanel(), BorderLayout.WEST);

        container.add(panel);
    }
}
