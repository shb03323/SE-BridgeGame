package controller;

import model.BridgeMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileController {
    public static BridgeMap initMap() throws Exception {
        // TODO : File name 입력하는 칸 만들고 default와 user input 구분하기
        BridgeMap bridgeMap = new BridgeMap();
        String currentWorkingDirectory = System.getProperty("user.dir");

        // load map
        try {
            BufferedReader reader = new BufferedReader(new FileReader(currentWorkingDirectory + "/src/assets/map/" + bridgeMap.getMapName()));

            // check the validation of map file
            String str;
            while ((str = reader.readLine()) != null) {
                if(!bridgeMap.inputSingleLineOnMapAndCheckAvailability(str)) {
                    throw new Exception("This map file has an error");
                }
            }
            reader.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        return bridgeMap;
    }
}
