package controller;

import model.BridgeMap;
import validator.MapValidator;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MapController {
    // map for using in the game
    private static BridgeMap bridgeMap;

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
    }

    private void drawMap() {

    }
}
