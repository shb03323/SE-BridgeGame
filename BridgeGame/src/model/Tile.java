package model;

public class Tile {
    private final char tileName;
    private Position position;
    private Character nextDirection;
    private Character previousDirection;

    public Tile(char tileName, Position position, Character nextDirection, Character previousDirection) {
        this.tileName = tileName;
        this.position = position;
        this.nextDirection = nextDirection;
        this.previousDirection = previousDirection;
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

    public Character getNextDirection() {
        return nextDirection;
    }

    public Character getPreviousDirection() { return previousDirection; }
}
