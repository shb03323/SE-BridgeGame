package view;

import controller.MapController;
import view.utils.Util;

import javax.swing.*;

public class MapPanel extends JPanel {
    public JPanel[] cells;
    public MapPanel(int width, int height, int size) {
        this.setBounds(150, 100, (width + 3) * 40, (height + 3) * 40);
        Util.changePanelWithBorder(this);
        this.setLayout(null);
        cells = new JPanel[size];

        MapController mapController = new MapController();
        for (int i = 0; i < size; i++) {
            cells[i] = mapController.drawCell(i);
            add(cells[i]);
        }
    }
}
