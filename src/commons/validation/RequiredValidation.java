package commons.validation;

import java.util.regex.Pattern;

public class RequiredValidation {
    private static final String REQUIRED_REGEX = "^\\w.*$";
    public static boolean requiredValidate(String string) {
        return Pattern.matches(REQUIRED_REGEX,string);
    }
}
