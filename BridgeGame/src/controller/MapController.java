package controller;

import model.BridgeMap;
import model.Tile;
import validator.MapValidator;
import view.ImageLabel;
import view.MapPanel;
import view.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MapController {
    // map for using in the game
    private static BridgeMap bridgeMap;

    // map view
    private MapPanel mapView;

    // block size
    private int blockSize = 40;

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

    public BridgeMap getMap() {
        return bridgeMap;
    }

    public MapPanel getPanel() {
        return mapView;
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
            panel.setBounds((tile.getPosition().x() - 1) * blockSize, tile.getPosition().y() * blockSize,blockSize * 2, blockSize * 2);
        } else if (i == bridgeMap.getMapTileList().size() - 1) {
            panel.add(new JLabel("END"));
            panel.setBounds(tile.getPosition().x() * blockSize, (tile.getPosition().y() - 1) * blockSize,blockSize * 2, blockSize * 2);
        } else if (tile.getTileName() == 'S') {
            panel.add(new ImageLabel("Saw", 30));
            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize,  blockSize);
        } else if (tile.getTileName() == 'H') {
            panel.add(new ImageLabel("Hammer", 30));
            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize,  blockSize);
        } else if (tile.getTileName() == 'P') {
            panel.add(new ImageLabel("PhilipsDriver", 30));
            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize,  blockSize);
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

            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize * 2,  blockSize);
        } else {
            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize,  blockSize);
        }

        if (tile.getTileName() != 'B') {
            panel.setBackground(new Color(255, 226, 143));
            Util.changePanelWithBorder(panel);
        }

        return panel;
    }

    public void initCharacter(int playerNum) {
        for (int i = 0; i < playerNum; i++) {
            JPanel panel = new JPanel();
            panel.add(new ImageLabel("player" + (i + 1), 30));
            panel.setName("player" + i);
            panel.setBounds((bridgeMap.getMapTileList().get(0).getPosition().x() - 1) * blockSize + i * blockSize / 2, (bridgeMap.getMapTileList().get(0).getPosition().y() + 1) * blockSize, blockSize, blockSize);
            panel.setVisible(true);
            mapView.add(panel);
        }
    }

    public void setCharacter(int i, int playerIndex) {
        JPanel panel = new JPanel();
    }

    public JPanel drawCharacter(int i, int playerIndex) {
        JPanel panel = new JPanel();
        Tile tile = bridgeMap.getMapTileList().get(i);
        int blockSize = 40;

        if (tile.getTileName() == 'B') {
            panel.setLayout(new GridLayout(1, 2));

            JPanel cross = new JPanel();
            cross.add(new ImageLabel("Player" + (playerIndex + 1), 30));
            cross.setBackground(new Color(255, 226, 143));
            Util.changePanelWithBorder(cross);
            panel.add(cross);

            JPanel bridge = new JPanel();
            bridge.add(new ImageLabel("Bridge", 40));
            panel.add(bridge);

            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize * 2, blockSize);
        } else {
            panel.add(new ImageLabel("Player" + (playerIndex + 1), 30));
            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize,  blockSize);
            panel.setBackground(new Color(255, 226, 143));
            Util.changePanelWithBorder(panel);
        }

        return panel;
    }
}
