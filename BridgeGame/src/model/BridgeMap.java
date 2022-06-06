package model;

import java.awt.*;
import java.util.LinkedList;

public class BridgeMap {
    // tile list of map
    private final LinkedList<Tile> mapTileList = new LinkedList<>();

    // width and height of map
    private int width;
    private int height;

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

    public int getTileIndexByPoint(Point point) {
        for (int i = 0; i < mapTileList.size(); i++) {
            if (mapTileList.get(i).getPoint().equals(point)) {
                return i;
            }
        }
        return 0;
    }
}
