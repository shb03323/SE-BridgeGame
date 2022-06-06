package model;

import java.awt.*;

public class Tile {
    private final char tileName;
    private Point point;
    private Character nextDirection;
    private Character previousDirection;

    public Tile(char tileName, Point point, Character nextDirection, Character previousDirection) {
        this.tileName = tileName;
        this.point = point;
        this.nextDirection = nextDirection;
        this.previousDirection = previousDirection;
    }

    public char getTileName() {
        return tileName;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public Character getNextDirection() {
        return nextDirection;
    }

    public Character getPreviousDirection() { return previousDirection; }

}
