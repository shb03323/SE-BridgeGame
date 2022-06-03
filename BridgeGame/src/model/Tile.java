package model;

public class Tile {
    private final char tileName;
    private Position position;
    private Character nextDirection;

    public Tile(char tileName, Position position, Character nextDirection) {
        this.tileName = tileName;
        this.position = position;
        this.nextDirection = nextDirection;
    }

    public char getTileName() {
        return tileName;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public char getNextDirection() {
        return nextDirection;
    }
}
