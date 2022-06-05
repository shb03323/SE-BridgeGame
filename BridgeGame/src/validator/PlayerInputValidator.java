package validator;

import model.BridgeMap;
import model.Position;
import model.Tile;

public class PlayerInputValidator extends Validator{
    // input of user
    private final String input;

    // dice number
    private int diceNumber;

    // map information
    private final BridgeMap bridgeMap;

    // index of tile
    private int tileIndex;

    public PlayerInputValidator(String input, int diceNumber, BridgeMap bridgeMap, int tileIndex) {
        this.input = input.replaceAll(" ", "").toUpperCase();
        this.diceNumber = diceNumber;
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

    // check diceNumber and input string's length is match
    private boolean checkInputLength() {
        return diceNumber == input.length();
    }

    // check the player input right direction.
    private boolean checkDirection() {
        int i = 0;
        while (i < diceNumber) {
            Tile tile = bridgeMap.getMapTileList().get(tileIndex);
            // check tile's next direction or previous direction and input is matching
            if (tile.getNextDirection() == input.charAt(i)) {
                tileIndex++;
            } else if (tile.getPreviousDirection() == input.charAt(i)) {
                tileIndex--;
            } else {
                // allow going right when player is on 'B' tile
                if (tile.getTileName() == 'B' && input.charAt(i) == 'R') {
                    tileIndex = bridgeMap.getTileIndexByPosition(new Position(tile.getPosition().x() + 2, tile.getPosition().y()));
                } else if (tile.getTileName() == 'b' && input.charAt(i) == 'L') {
                    tileIndex = bridgeMap.getTileIndexByPosition(new Position(tile.getPosition().x() - 2, tile.getPosition().y()));
                } else {
                    return false;
                }
            }
            i++;
        }
        return true;
    }
}
