package view;

import controller.MainController;
import controller.MapController;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel implements MapObserver {
    public JPanel[] cells;
    public MapPanel(int width, int height, int size) {
        this.setLayout(null);
        cells = new JPanel[size];

        MapController mapController = new MapController();
        for (int i = 0; i < size; i++) {
            cells[i] = mapController.drawCell(i);
            add(cells[i]);
        }
    }
}
