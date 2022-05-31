package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileController {
    // name of map
    private String mapName;

    // buffered reader to read file
    BufferedReader reader;

    public FileController(String mapName) throws FileNotFoundException {
        this.mapName = mapName;
        reader = new BufferedReader(new FileReader("../assets/map/" + mapName));
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void closeReader() throws IOException {
        reader.close();
    }
}
