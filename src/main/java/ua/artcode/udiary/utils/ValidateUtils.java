package ua.artcode.udiary.utils;

// todo write a test
public class ValidateUtils {


    private static final String EMAIL_PATTERN ="\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$\"";
    private static final String PASS_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"; // Minimum eight characters, at least one letter and one number:

    // todo hard to understand what went wrong
    // todo throw exception instead of returing boolean
    public static boolean loginValidator(String email, String pass){
        return !(email == null || pass == null || !email.matches(EMAIL_PATTERN) || !pass.matches(PASS_PATTERN));
    }
}
