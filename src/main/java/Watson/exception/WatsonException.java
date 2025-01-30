package Watson.exception;
/**
 * Custom exception class for Watson application-specific errors.
 */
public class WatsonException extends Exception{
    /**
     Constructs a WatsonException with a detailed message.
     @param message The error message describing the exception cause.
     */
    public WatsonException(String message){
        super(message);
    }
}
