package site.devdalus.ariadne.exception;

public class ResourceNotExistException extends RuntimeException {
    public ResourceNotExistException() {
        super();
    }

    public ResourceNotExistException(String message) {
        super(message);
    }
}
