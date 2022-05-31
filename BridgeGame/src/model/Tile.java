package model;

import model.Position;

public record
Tile(
        char tileName,
        Position position,
        Character nextDirection
) { }
