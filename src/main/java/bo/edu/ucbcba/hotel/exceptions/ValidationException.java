package bo.edu.ucbcba.hotel.exceptions;



public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super("Validation error: " + message);
    }
}

