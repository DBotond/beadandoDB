package employees.exceptions;

public class WrongDateException extends Exception{
    public WrongDateException() {
    }

    public WrongDateException(String message) {
        super(message);
    }
}
