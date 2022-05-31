package model;

import java.util.LinkedList;

public class BridgeMap {
    // map name
    private String mapName = "default.map";

    // tile list of map
    private LinkedList<Tile> mapTileList = new LinkedList<>();

    // width and height of map
    private int width;
    private int height;

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMapName() {
        return mapName;
    }

    public LinkedList<Tile> getMapTileList() { return mapTileList; }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
