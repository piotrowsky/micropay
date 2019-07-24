package example.micropay.domain;

public class TransferFailureException extends RuntimeException {

    TransferFailureException(Throwable cause) {
        super(cause);
    }

    public TransferFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
