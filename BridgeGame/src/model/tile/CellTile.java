package model.tile;

public class CellTile extends Tile {
    public CellTile(int x, int y) {
        this.tileName = 'C';
        this.position = new Position(x, y);
    }
}
