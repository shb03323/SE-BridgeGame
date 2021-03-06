package controller;

import model.map.BridgeMap;
import model.map.Tile;
import validator.MapValidator;
import view.ImageLabel;
import view.MapPanel;
import view.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MapController {
    // map for using in the game
    private static BridgeMap bridgeMap;

    // map view
    private MapPanel mapView;

    // map file name
    private String mapName;

    // block size
    private int blockSize = 40;

    // init map
    public void initMap(String previousMap) throws Exception {
        // maps in the map directory
        String currentWorkingDirectory = System.getProperty("user.dir");
        File dir = new File(currentWorkingDirectory + "/src/assets/map/");
        String[] mapNames = dir.list();

        /**
         * shows a dialog to help user select a map
         * when game is restart, choose the map that used in previous game
         */
        mapName = (String) JOptionPane.showInputDialog(null, "Choose the map", null, JOptionPane.PLAIN_MESSAGE, null, mapNames, previousMap);
        bridgeMap = new BridgeMap();

        // load map
        FileReader fReader = null;
        try {
            fReader = new FileReader(currentWorkingDirectory + "/src/assets/map/" + mapName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fReader != null;

        // check map is validate
        MapValidator mapValidator = new MapValidator(new BufferedReader(fReader), bridgeMap);
        if (mapValidator.validate()) {
            bridgeMap = mapValidator.getValidatedMap();
        } else {
            JOptionPane.showMessageDialog(null, "Map has error.\nTerminate the program.");
            System.exit(0);
        }

        // finished validation, so draw it
        drawMap();
    }

    public BridgeMap getMap() {
        return bridgeMap;
    }

    public MapPanel getPanel() {
        return mapView;
    }

    public String getMapName() {
        return mapName;
    }

    private void drawMap() {
        mapView = new MapPanel(bridgeMap.getWidth(), bridgeMap.getHeight(), bridgeMap.getMapTileList().size());
    }

    // draw single cell
    public JPanel drawCell(int i) {
        JPanel panel = new JPanel();
        Tile tile = bridgeMap.getMapTileList().get(i);

        if (i == 0) {
            panel.add(new JLabel("START"));
            panel.setBounds((tile.getPoint().x - 1) * blockSize, tile.getPoint().y * blockSize,blockSize * 2, blockSize * 2);
        } else if (i == bridgeMap.getMapTileList().size() - 1) {
            panel.add(new JLabel("END"));
            panel.setBounds(tile.getPoint().x * blockSize, (tile.getPoint().y - 1) * blockSize,blockSize * 2, blockSize * 2);
        } else if (tile.getTileName() == 'S') {
            panel.add(new ImageLabel("Saw", 30));
            panel.setBounds(tile.getPoint().x * blockSize, tile.getPoint().y * blockSize, blockSize,  blockSize);
        } else if (tile.getTileName() == 'H') {
            panel.add(new ImageLabel("Hammer", 30));
            panel.setBounds(tile.getPoint().x * blockSize, tile.getPoint().y * blockSize, blockSize,  blockSize);
        } else if (tile.getTileName() == 'P') {
            panel.add(new ImageLabel("PhilipsDriver", 30));
            panel.setBounds(tile.getPoint().x * blockSize, tile.getPoint().y * blockSize, blockSize,  blockSize);
        } else if (tile.getTileName() == 'B') {
            panel.setLayout(new GridLayout(1, 2));

            JPanel cross = new JPanel();
            cross.add(new ImageLabel("Cross", 30));
            cross.setBackground(new Color(255, 226, 143));
            Util.changePanelWithBorder(cross);
            panel.add(cross);

            JPanel bridge = new JPanel();
            bridge.add(new ImageLabel("Bridge", 40));
            panel.add(bridge);

            panel.setBounds(tile.getPoint().x * blockSize, tile.getPoint().y * blockSize, blockSize * 2,  blockSize);
        } else {
            panel.setBounds(tile.getPoint().x * blockSize, tile.getPoint().y * blockSize, blockSize,  blockSize);
        }

        if (tile.getTileName() != 'B') {
            panel.setBackground(new Color(255, 226, 143));
            Util.changePanelWithBorder(panel);
        }

        return panel;
    }

    // init character icon in the map GUI
    public void initCharacter(int playerNum) {
        for (int i = 0; i < playerNum; i++) {
            JPanel panel = new JPanel();
            panel.add(new ImageLabel("Player" + (i + 1), 20));
            panel.setName("player" + i);
            panel.setBounds((bridgeMap.getMapTileList().get(0).getPoint().x - 1) * blockSize + i * blockSize / 2, (bridgeMap.getMapTileList().get(0).getPoint().y + 1) * blockSize, blockSize - 10, blockSize - 10);
            panel.setBackground(new Color(155, 188, 212));
            Util.changePanelWithBorder(panel);
            mapView.add(panel, i);
        }
    }

    // move character icon in the map
    public void setCharacter(int i, int playerIndex) {
        JPanel panel = (JPanel) mapView.getComponent(playerIndex);
        Tile tile = bridgeMap.getMapTileList().get(i);
        panel.setBounds(tile.getPoint().x * blockSize + playerIndex * 5, tile.getPoint().y * blockSize + 5, blockSize - 10, blockSize - 10);
    }
}
