package exceptions;

/**
 * Created by sai on 12/08/2015.
 */
public class CarDoesNotExistException extends RuntimeException {

    public CarDoesNotExistException(String message) {
        super(message);
    }
}
