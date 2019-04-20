package be.valuya.bob.core.domain;

public class BobException extends RuntimeException {
    public BobException() {
    }

    public BobException(String message) {
        super(message);
    }

    public BobException(String message, Throwable cause) {
        super(message, cause);
    }

    public BobException(Throwable cause) {
        super(cause);
    }

    public BobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
