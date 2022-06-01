package validator;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Validator {
    // check the validation of map file
    public abstract boolean validate() throws Exception;
}
