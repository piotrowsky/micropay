package org.micropay.domain.transfer.validation;

public class InvalidAmountException extends RuntimeException {
    InvalidAmountException(String message) {
        super(message);
    }
}
