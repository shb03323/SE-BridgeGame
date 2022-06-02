package validator;

import model.player.Player;

public class PlayerCanStayValidator extends Validator {
    private final Player player;

    public PlayerCanStayValidator(Player player) {
        this.player = player;
    }

    /**
     * If player do not have bridge card,
     * throw exception.
     */
    @Override
    public boolean validate() throws Exception {
        return player.getBridgeCardNum() > 0;
    }
}
