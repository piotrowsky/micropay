package org.micropay.domain.transfer;

public class TransferFailureException extends RuntimeException {

    TransferFailureException(Throwable cause) {
        super(cause);
    }
}
