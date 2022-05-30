package model.tile;

public class BridgeTile extends Tile {
    public BridgeTile(char tileName, int x, int y) {
        this.tileName = tileName;
        this.position = new Position(x, y);
    }
}
