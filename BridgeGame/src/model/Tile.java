package model;

public record Tile(
        char tileName,
        Position position,
        Character nextDirection
) { }
