package model;

import model.Tile;

import java.util.LinkedList;

public class BridgeMap {
    // Linked List of all tiles
    private LinkedList<Tile> tileList;

    // map's maximum and minimum position of x, y
    private int maxX = 0;
    private int minX = 0;
    private int maxY = 0;
    private int minY = 0;

    public LinkedList<Tile> getTileList() {
        return tileList;
    }

    public int getHeight() {
        return maxY - minY;
    }

    public int getWidth() {
        return maxX - minX;
    }

    public boolean inputSingleLineOnMapAndCheckAvailability(String inputString) {
        // remove spaces in input string
        inputString = inputString.replaceAll(" ", "");

        // check if input is valid
        if (!checkInputAvailable(inputString)) {
            return false;
        }

        // make the tile
        Tile tile;
        if (tileList.isEmpty()) {
            if (inputString.charAt(0) != 'S') {
                return false;
            }
            tile = new Tile(inputString.charAt(0), new Position(0, 0), inputString.charAt(1));
        } else {
            if (inputString.charAt(0) == 'S') {
                return false;
            } else if (inputString.charAt(0) == 'E') {
                tile = new Tile(inputString.charAt(0), getPresentPosition(), null);
            } else {
                tile = new Tile(inputString.charAt(0), getPresentPosition(), inputString.charAt(2));
            }
        }

        // check if tile direction is valid
        if (!checkDirectionAvailable(inputString.charAt(1))) {
            return false;
        }

        // check if tile is valid
        if (!checkTileAvailable(tile)) {
            return false;
        }

        // set max and min of x, y
        setMaxAndMinOfXY(tile.position());

        // add the tile in tile list
        tileList.add(tile);

        return true;
    }

    // check if the max or min of x, y is changed
    private void setMaxAndMinOfXY(Position position) {
        if (position.x() > maxX) {
            maxX = position.x();
        } else if (position.x() < minX) {
            minX = position.x();
        }

        if (position.y() > maxY) {
            maxY = position.y();
        } else if (position.y() < minY) {
            minY = position.y();
        }
    }

    // get the position of present tile
    private Position getPresentPosition() {
        int previousX = tileList.getLast().position().x();
        int previousY = tileList.getLast().position().y();

        if (tileList.getLast().nextDirection() == 'U') {
            return new Position(previousX, previousY + 1);
        } else if (tileList.getLast().nextDirection() == 'D') {
            return new Position(previousX, previousY - 1);
        } else if (tileList.getLast().nextDirection() == 'R') {
            return new Position(previousX + 1, previousY);
        } else {
            return new Position(previousX - 1, previousY);
        }
    }

    /**
     * Check the map input is available.
     * "E" -> only you can input single "E"
     * "S" -> you can input type of tile and next direction(s)
     * "B", "b", "C", "P", "H" -> you can only input type of tile and adjacent tile's direction
     */
    private boolean checkInputAvailable(String inputString) {
        if (inputString.charAt(0) == 'E') {
            return inputString.length() == 1;
        } else if (inputString.charAt(0) == 'S') {
            return inputString.length() == 2 || inputString.length() == 3;
        } else if (inputString.charAt(0) == 'B' || inputString.charAt(0) == 'b' || inputString.charAt(0) == 'C' || inputString.charAt(0) == 'P' || inputString.charAt(0) == 'H') {
            return inputString.length() == 3;
        } else {
            return false;
        }
    }

    // check if the next direction of the previous tile is opposite to the previous direction of the current tile
    private boolean checkDirectionAvailable(char previousDirectionOfNow) {
        if (previousDirectionOfNow == 'D') {
            return tileList.getLast().nextDirection() == 'U';
        } else if (previousDirectionOfNow == 'U') {
            return tileList.getLast().nextDirection() == 'D';
        } else if (previousDirectionOfNow == 'L') {
            return tileList.getLast().nextDirection() == 'R';
        } else if (previousDirectionOfNow == 'R') {
            return tileList.getLast().nextDirection() == 'L';
        } else {
            return false;
        }
    }

    /**
     * 1. Make sure you place a tile where it has already been placed.
     * 2. Check that the starting and ending points of the bridge match well.
     */
    private boolean checkTileAvailable(Tile tile) {
        if (tile.tileName() == 'b') {
            for (int i = tileList.size() - 1; i >= 0; i--) {
                if (tileList.get(i).tileName() == 'B' && tileList.get(i).position().x() == tile.position().x() - 2) {
                    return false;
                }

                if (tileList.get(i).position().equals(tile.position())) {
                    return false;
                }
            }
        } else {
            for (int i = tileList.size() - 1; i >= 0; i--) {
                if (tileList.get(i).position().equals(tile.position())) {
                    return false;
                }
            }
        }

        return true;
    }
}
