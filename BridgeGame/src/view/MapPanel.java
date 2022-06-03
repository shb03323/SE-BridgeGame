package view;

import controller.MainController;
import controller.MapController;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel implements MapObserver {
    public GridBagConstraints[] gridBagConstraints;
    public JPanel[] cells;
    public MapPanel(int width, int height, int size) {
        gridBagConstraints = new GridBagConstraints[size];
        cells = new JPanel[size];
        this.setLayout(new GridBagLayout());

        MapController mapController = new MapController();
        for (int i = 0; i < size; i++) {
            cells[i] = (JPanel) mapController.drawConstraints(i)[0];
            gridBagConstraints[i] = (GridBagConstraints) mapController.drawConstraints(i)[1];
            this.add(cells[i], gridBagConstraints[i]);
        }
    }
}
