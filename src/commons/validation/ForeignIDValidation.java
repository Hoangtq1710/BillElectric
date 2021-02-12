package commons.validation;

import java.util.regex.Pattern;

public class ForeignIDValidation {
    private static final String FOREIGN_ID_REGEX = "^KHNN-[\\d]{5}$";
    public static boolean foreignIDValidate(String id) {
        return Pattern.matches(FOREIGN_ID_REGEX,id);
    }
}
