package validator;

import model.BridgeMap;
import model.Tile;

import java.awt.*;

public class PlayerInputValidator extends Validator{
    // input of user
    private final String input;

    // dice number
    private int diceNumber;

    // bridge card number
    private int bridgeCardNum;

    // bridgeCardNum of now
    int addedBridgeCardNum = 0;

    // map information
    private final BridgeMap bridgeMap;

    // index of tile
    private int tileIndex;

    public PlayerInputValidator(String input, int diceNumber, int bridgeCardNum, BridgeMap bridgeMap, int tileIndex) {
        this.input = input.replaceAll(" ", "").toUpperCase();
        this.diceNumber = diceNumber;
        this.bridgeCardNum = bridgeCardNum;
        this.bridgeMap = bridgeMap;
        this.tileIndex = tileIndex;
    }

    @Override
    public boolean validate() throws Exception {
        return checkInputLength() && checkDirection();
    }

    public int getTileIndex() {
        return tileIndex;
    }

    public int getAddedBridgeCardNum() { return addedBridgeCardNum; }

    // check diceNumber and input string's length is match
    private boolean checkInputLength() {
        if (diceNumber - bridgeCardNum <= 0) {
            return input.length() == 0;
        } else {
            return input.length() == diceNumber - bridgeCardNum;
        }
    }

    // check the player input right direction.
    private boolean checkDirection() {
        int i = 0;
        while (i < diceNumber - bridgeCardNum) {
            Tile tile = bridgeMap.getMapTileList().get(tileIndex);

            // when player get to end
            if (tile.getTileName() == 'E') {
                break;
            }

            // check tile's next direction or previous direction and input is matching
            if (tile.getNextDirection() == input.charAt(i)) {
                tileIndex++;
            } else if (tile.getPreviousDirection() == input.charAt(i)) {
                tileIndex--;
            } else {
                // allow going right when player is on 'B' tile
                if (tile.getTileName() == 'B' && input.charAt(i) == 'R') {
                    tileIndex = bridgeMap.getTileIndexByPoint(new Point(tile.getPoint().x + 2, tile.getPoint().y));
                    addedBridgeCardNum++;
                } else if (tile.getTileName() == 'b' && input.charAt(i) == 'L') {
                    tileIndex = bridgeMap.getTileIndexByPoint(new Point(tile.getPoint().x - 2, tile.getPoint().y));
                    addedBridgeCardNum++;
                } else {
                    return false;
                }
            }
            i++;
        }
        return true;
    }
}
