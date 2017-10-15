package ua.artcode.udiary.utils;

public class ValidateUtils {

    private static final String EMAIL_PATTERN ="\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$\"";
    private static final String PASS_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"; // Minimum eight characters, at least one letter and one number:

    public static boolean loginValidator(String email, String pass){
        if (email == null || pass == null || !email.matches(EMAIL_PATTERN) || !pass.matches(PASS_PATTERN)){
            return false;
        } else {
            return true;
        }
    }
}
