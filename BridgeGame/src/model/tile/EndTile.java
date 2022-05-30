package model.tile;

public class EndTile extends Tile {
    public EndTile(int x, int y) {
        this.tileName = 'E';
        this.position = new Position(x, y);
    }
}
