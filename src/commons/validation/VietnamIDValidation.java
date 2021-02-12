package commons.validation;

import java.util.regex.Pattern;

public class VietnamIDValidation {
    private static final String VN_ID_REGEX = "^KHVN-[\\d]{5}$";
    public static boolean vietnamIDValidate(String id) {
        return Pattern.matches(VN_ID_REGEX,id);
    }
}
