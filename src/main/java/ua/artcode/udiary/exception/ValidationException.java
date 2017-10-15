package ua.artcode.udiary.exception;

/**
 * Class of specified exception for validation logic.
 *
 * @author alex323glo
 * @version 1.0.0   created on 12.10.2017 (20:33)
 *
 * @see AppException
 */
public class ValidationException extends AppException {
    public ValidationException(String message) {
        super(message);
    }
}
