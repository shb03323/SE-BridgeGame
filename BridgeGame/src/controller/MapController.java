package controller;

import model.BridgeMap;
import validator.MapValidator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MapController {
    // map for using in the game
    private static BridgeMap bridgeMap;

    public void initMap() throws Exception {
        // TODO : File name 입력하는 칸 만들고 default와 user input 구분하기
        bridgeMap = new BridgeMap();
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
        }
    }
}
