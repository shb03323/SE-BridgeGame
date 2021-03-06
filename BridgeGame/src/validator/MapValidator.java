package validator;

import model.map.BridgeMap;
import model.map.Tile;

import java.awt.*;
import java.io.BufferedReader;

public class MapValidator extends Validator {
    private final BridgeMap bridgeMap;
    private final BufferedReader reader;

    // map's maximum and minimum point of x, y
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
        try {
            while ((str = reader.readLine()) != null) {
                if(!checkSingleLineOnMapAvailability(str)) {
                    throw new Exception("This map file has an error");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            reader.close();
        }

        // set map's height and width
        bridgeMap.setWidth(maxX - minX);
        bridgeMap.setHeight(maxY - minY);

        for (Tile tile : bridgeMap.getMapTileList()) {
            tile.setPoint(new Point(tile.getPoint().x - minX + 1, tile.getPoint().y - minY + 1));
        }
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
            // start tile
            if (inputString.charAt(0) != 'S') {
                return false;
            }
            tile = new Tile(inputString.charAt(0), new Point(1, 0), inputString.charAt(1), null);
        } else {
            // end tile
            if (inputString.charAt(0) == 'E') {
                tile = new Tile(inputString.charAt(0), getPresentgetPoint(), null, null);
            } else {
                // other tiles
                tile = new Tile(inputString.charAt(0), getPresentgetPoint(), inputString.charAt(2), inputString.charAt(1));

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
        setMaxAndMinOfXY(tile.getPoint());

        // add the tile in tile list
        bridgeMap.getMapTileList().add(tile);

        return true;
    }

    // check if the max or min of x, y is changed
    private void setMaxAndMinOfXY(Point point) {
        if (point.x > maxX) {
            maxX = point.x;
        } else if (point.x < minX) {
            minX = point.x;
        }

        if (point.y > maxY) {
            maxY = point.y;
        } else if (point.y < minY) {
            minY = point.y;
        }
    }

    // get the point of present tile
    private Point getPresentgetPoint() {
        int previousX = bridgeMap.getMapTileList().getLast().getPoint().x;
        int previousY = bridgeMap.getMapTileList().getLast().getPoint().y;

        if (bridgeMap.getMapTileList().getLast().getNextDirection() == 'U') {
            return new Point(previousX, previousY - 1);
        } else if (bridgeMap.getMapTileList().getLast().getNextDirection() == 'D') {
            return new Point(previousX, previousY + 1);
        } else if (bridgeMap.getMapTileList().getLast().getNextDirection() == 'R') {
            return new Point(previousX + 1, previousY);
        } else {
            return new Point(previousX - 1, previousY);
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
            return bridgeMap.getMapTileList().getLast().getNextDirection() == 'U';
        } else if (previousDirectionOfNow == 'U') {
            return bridgeMap.getMapTileList().getLast().getNextDirection() == 'D';
        } else if (previousDirectionOfNow == 'L') {
            return bridgeMap.getMapTileList().getLast().getNextDirection() == 'R';
        } else if (previousDirectionOfNow == 'R') {
            return bridgeMap.getMapTileList().getLast().getNextDirection() == 'L';
        } else {
            return false;
        }
    }

    /**
     * 1. Make sure you place a tile where it has already been placed.
     * 2. Check that the starting and ending points of the bridge match well.
     */
    private boolean checkTileAvailable(Tile tile) {
        if (tile.getTileName() == 'b') {
            // flag for checking bridge validity
            boolean flag = false;
            for (int i = bridgeMap.getMapTileList().size() - 1; i >= 0; i--) {
                if (bridgeMap.getMapTileList().get(i).getTileName() == 'B' && bridgeMap.getMapTileList().get(i).getPoint().x == tile.getPoint().x - 2) {
                    flag = true;
                }

                if (bridgeMap.getMapTileList().get(i).getPoint().equals(tile.getPoint())) {
                    return false;
                }
            }
            return flag;
        } else {
            for (int i = bridgeMap.getMapTileList().size() - 1; i >= 0; i--) {
                if (bridgeMap.getMapTileList().get(i).getPoint().equals(tile.getPoint())) {
                    return false;
                }
            }
        }

        return true;
    }
}