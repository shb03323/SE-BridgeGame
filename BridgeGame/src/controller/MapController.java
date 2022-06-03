package controller;

import model.BridgeMap;
import model.Tile;
import validator.MapValidator;
import view.ImagePanel;
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
    public JPanel drawConstraints(int i) {
        JPanel panel = new JPanel();
        Tile tile = bridgeMap.getMapTileList().get(i);
        int blockSize = 50;

        if (i == 0) {
            panel.setBounds((tile.getPosition().x() - 1) * blockSize, tile.getPosition().y() * blockSize,blockSize * 2, blockSize * 2);
        } else if (i == bridgeMap.getMapTileList().size() - 1) {
            panel.setBounds(tile.getPosition().x() * blockSize, (tile.getPosition().y() - 1) * blockSize,blockSize * 2, blockSize * 2);
        } else if (tile.getTileName() == 'S') {
            Image image = new ImageIcon(System.getProperty("user.dir") + "/src/assets/tile/Saw.PNG").getImage();
            panel.add(new ImagePanel(image, blockSize));
            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize,  blockSize);
        } else if (tile.getTileName() == 'H') {
            Image image = new ImageIcon(System.getProperty("user.dir") + "/src/assets/tile/Hammer.PNG").getImage();
            panel = new ImagePanel(image, blockSize);
            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize,  blockSize);
        } else if (tile.getTileName() == 'P') {
            Image image = new ImageIcon(System.getProperty("user.dir") + "/src/assets/tile/PhilipsDriver.PNG").getImage();
            panel = new ImagePanel(image, blockSize);
            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize,  blockSize);
        } else if (tile.getTileName() == 'B') {
            Image image = new ImageIcon(System.getProperty("user.dir") + "/src/assets/tile/Cross.PNG").getImage();
            panel = new ImagePanel(image, blockSize);
            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize,  blockSize);
        } else {
            panel.setBounds(tile.getPosition().x() * blockSize, tile.getPosition().y() * blockSize, blockSize,  blockSize);
        }

        panel.setBackground(Color.yellow);
        Util.changePanelWithBorder(panel);

        return panel;
    }
}
