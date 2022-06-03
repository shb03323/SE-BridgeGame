package controller;

import model.BridgeMap;
import model.Tile;
import validator.MapValidator;
import view.MapObserver;
import view.MapPanel;
import view.PlayerScoreBoardObserver;
import view.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

public class MapController {
    // map for using in the game
    private static BridgeMap bridgeMap;

    // map view
    private MapObserver mapObserver;

    public void initMap() throws Exception {
        // FIXME : you can change the map in here.
        bridgeMap = new BridgeMap("default.map");
        String currentWorkingDirectory = System.getProperty("user.dir");

        // load map
        FileReader fReader = null;
        try {
            fReader = new FileReader(currentWorkingDirectory + "/src/assets/map/" + bridgeMap.getMapName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert fReader != null;
        MapValidator mapValidator = new MapValidator(new BufferedReader(fReader), bridgeMap);
        if (mapValidator.validate()) {
            bridgeMap = mapValidator.getValidatedMap();
        } else {
            JOptionPane.showMessageDialog(null, "Map has error.\nTerminate the program.");
            System.exit(0);
        }

        drawMap();
    }

    public MapObserver getPanel() {
        return mapObserver;
    }

    private void drawMap() {
        mapObserver = new MapPanel(bridgeMap.getWidth(), bridgeMap.getHeight(), bridgeMap.getMapTileList().size());
    }

    // draw single cell
    public Object[] drawConstraints(int i) {
        Object[] o = new Object[2];
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JPanel panel = new JPanel();
        Tile tile = bridgeMap.getMapTileList().get(i);

        if (i == 0) {
            gridBagConstraints.gridx = tile.position().x() - 1;
            gridBagConstraints.gridy = tile.position().y();
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridheight = 2;
        } else if (i == bridgeMap.getMapTileList().size() - 1) {
            gridBagConstraints.gridx = tile.position().x();
            gridBagConstraints.gridy = tile.position().y() + 1;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridheight = 2;
        } else if (tile.tileName() == 'S') {
            gridBagConstraints.gridx = tile.position().x();
            gridBagConstraints.gridy = tile.position().y();
            panel = new JPanel() {
                final Image background = new ImageIcon(System.getProperty("user.dir") + "/src/assets/tile/Saw.PNG").getImage();
                public void paint(Graphics g) {
                    g.drawImage(background, 0, 0, null);
                }
            };
        } else if (tile.tileName() == 'H') {
            gridBagConstraints.gridx = tile.position().x();
            gridBagConstraints.gridy = tile.position().y();
            panel = new JPanel() {
                final Image background = new ImageIcon(System.getProperty("user.dir") + "/src/assets/tile/Hammer.PNG").getImage();
                public void paint(Graphics g) {
                    g.drawImage(background, 0, 0, null);
                }
            };
        } else if (tile.tileName() == 'P') {
            gridBagConstraints.gridx = tile.position().x();
            gridBagConstraints.gridy = tile.position().y();
            panel = new JPanel() {
                final Image background = new ImageIcon(System.getProperty("user.dir") + "/src/assets/tile/PhilipsDriver.PNG").getImage();
                public void paint(Graphics g) {
                    g.drawImage(background, 0, 0, null);
                }
            };
        } else if (tile.tileName() == 'B') {
            gridBagConstraints.gridx = tile.position().x();
            gridBagConstraints.gridy = tile.position().y();
            panel = new JPanel() {
                final Image background = new ImageIcon(System.getProperty("user.dir") + "/src/assets/tile/Cross.PNG").getImage();
                public void paint(Graphics g) {
                    g.drawImage(background, 0, 0, null);
                }
            };
        }

        panel.setBackground(Color.yellow);
        Util.changePanelWithBorder(panel);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        o[0] = panel;
        o[1] = gridBagConstraints;

        return o;
    }
}
