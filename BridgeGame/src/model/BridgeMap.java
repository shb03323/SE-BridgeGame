package model;

import java.util.LinkedList;

public class BridgeMap {
    // map name
    private final String mapName;

    // tile list of map
    private final LinkedList<Tile> mapTileList = new LinkedList<>();

    // width and height of map
    private int width;
    private int height;

    public BridgeMap(String mapName) {
        this.mapName = mapName;
    }

    public LinkedList<Tile> getMapTileList() { return mapTileList; }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height + 1;
    }

    public void setWidth(int width) {
        this.width = width + 1;
    }

    public int getTileIndexByPosition(Position position) {
        for (int i = 0; i < mapTileList.size(); i++) {
            if (mapTileList.get(i).getPosition().equals(position)) {
                return i;
            }
        }
        return 0;
    }
}
