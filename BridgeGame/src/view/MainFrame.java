package view;

import controller.MapController;
import controller.PlayerController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(PlayerController playerController, MapController mapController) {
        setTitle("Bridge Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initPanel(playerController, mapController);

        setWindow();
    }

    private void setWindow() {
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initPanel(PlayerController playerController, MapController mapController) {
        Container container = this.getContentPane();

        // set border layout
        JPanel panel = new JPanel(new BorderLayout(100, 100));

        panel.add((Component) playerController.getPanel(), BorderLayout.WEST);

        container.add(panel);
    }
}
