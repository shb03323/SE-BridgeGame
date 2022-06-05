package view;

import controller.MapController;
import model.player.Player;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    public JPanel[] cells;
    public MapPanel(int width, int height, int size) {
        this.setPreferredSize(new Dimension(width * 40, height * 40));
        this.setLayout(null);
        cells = new JPanel[size];

        MapController mapController = new MapController();
        for (int i = 0; i < size; i++) {
            cells[i] = mapController.drawCell(i);
            add(cells[i]);
        }
    }
}
