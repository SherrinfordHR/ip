package Watson.exception;

/**
 * Custom exception class for Watson application-specific errors.
 * Thrown for invalid user input, task operations, or file handling issues.
 */
public class WatsonException extends Exception {
    /**
     * Constructs a WatsonException with a detailed message.
     *
     * @param message The error message describing the exception cause.
     */
    public WatsonException(String message) {
        super(message);
    }
}