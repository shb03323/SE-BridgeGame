package view;

import controller.MapController;
import controller.PlayerController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // main frame
    public MainFrame(PlayerController playerController, MapController mapController) {
        setTitle("Bridge Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initPanel(playerController, mapController);

        setWindow();
    }

    private void setWindow() {
        setSize(1035, 1035);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // init score board, map, player input panel
    private void initPanel(PlayerController playerController, MapController mapController) {
        Container container = this.getContentPane();

        // set layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.add(playerController.getScoreBoardPanel());
        panel.add(mapController.getPanel());
        panel.add(playerController.getInputPanel());

        container.add(panel);
    }
}
