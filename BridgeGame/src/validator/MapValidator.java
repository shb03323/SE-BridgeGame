package validator;

import model.BridgeMap;
import model.Position;
import model.Tile;

import java.io.BufferedReader;

public class MapValidator extends Validator {
    private final BridgeMap bridgeMap;
    private final BufferedReader reader;

    // map's maximum and minimum position of x, y
    private int maxX = 0;
    private int minX = 0;
    private int maxY = 0;
    private int minY = 0;

    public MapValidator(BufferedReader reader, BridgeMap bridgeMap) {
        this.reader = reader;
        this.bridgeMap = bridgeMap;
    }

    // check the validation of map file
    @Override
    public boolean validate() throws Exception {
        String str;
        while ((str = reader.readLine()) != null) {
            if(!checkSingleLineOnMapAvailability(str)) {
                throw new Exception("This map file has an error");
            }
        }
        reader.close();

        // set map's height and width
        bridgeMap.setWidth(maxX - minX);
        bridgeMap.setHeight(maxY - minY);
        return true;
    }

    public BridgeMap getValidatedMap() {
        return bridgeMap;
    }

    // check single line of input
    private boolean checkSingleLineOnMapAvailability(String inputString) {
        // remove spaces in input string
        inputString = inputString.replaceAll(" ", "");

        // check if input is valid
        if (!checkInputAvailable(inputString)) {
            return false;
        }

        // make the tile
        Tile tile;
        if (bridgeMap.getMapTileList().isEmpty()) {
            if (inputString.charAt(0) != 'S') {
                return false;
            }
            tile = new Tile(inputString.charAt(0), new Position(0, 0), inputString.charAt(1));
        } else {
            if (inputString.charAt(0) == 'E') {
                tile = new Tile(inputString.charAt(0), getPresentPosition(), null);
            } else {
                tile = new Tile(inputString.charAt(0), getPresentPosition(), inputString.charAt(2));

                // check if tile direction is valid
                if (!checkDirectionAvailable(inputString.charAt(1))) {
                    return false;
                }
            }

            // check if tile is valid
            if (!checkTileAvailable(tile)) {
                return false;
            }
        }

        // set max and min of x, y
        setMaxAndMinOfXY(tile.position());

        // add the tile in tile list
        bridgeMap.getMapTileList().add(tile);

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
        int previousX = bridgeMap.getMapTileList().getLast().position().x();
        int previousY = bridgeMap.getMapTileList().getLast().position().y();

        if (bridgeMap.getMapTileList().getLast().nextDirection() == 'U') {
            return new Position(previousX, previousY + 1);
        } else if (bridgeMap.getMapTileList().getLast().nextDirection() == 'D') {
            return new Position(previousX, previousY - 1);
        } else if (bridgeMap.getMapTileList().getLast().nextDirection() == 'R') {
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
            return bridgeMap.getMapTileList().getLast().nextDirection() == 'U';
        } else if (previousDirectionOfNow == 'U') {
            return bridgeMap.getMapTileList().getLast().nextDirection() == 'D';
        } else if (previousDirectionOfNow == 'L') {
            return bridgeMap.getMapTileList().getLast().nextDirection() == 'R';
        } else if (previousDirectionOfNow == 'R') {
            return bridgeMap.getMapTileList().getLast().nextDirection() == 'L';
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
            // flag for checking bridge validity
            boolean flag = false;
            for (int i = bridgeMap.getMapTileList().size() - 1; i >= 0; i--) {
                if (bridgeMap.getMapTileList().get(i).tileName() == 'B' && bridgeMap.getMapTileList().get(i).position().x() == tile.position().x() - 2) {
                    flag = true;
                }

                if (bridgeMap.getMapTileList().get(i).position().equals(tile.position())) {
                    return false;
                }
            }
            return flag;
        } else {
            for (int i = bridgeMap.getMapTileList().size() - 1; i >= 0; i--) {
                if (bridgeMap.getMapTileList().get(i).position().equals(tile.position())) {
                    return false;
                }
            }
        }

        return true;
    }
}