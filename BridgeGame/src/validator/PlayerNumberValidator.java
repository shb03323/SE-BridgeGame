package validator;

public class PlayerNumberValidator extends Validator {
    // input of player number
    private String input;

    public PlayerNumberValidator(String input) {
        this.input = input;
    }

    /**
     * When player number input can't parse to int,
     * throw exception.
     * Also, when player number input is not in 2~4,
     * throw exception.
     */
    @Override
    public boolean validate() throws Exception {
        try {
            int num = Integer.parseInt(input);
            if (num > 4 || num < 2) {
                throw new Exception("Undefined input");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
