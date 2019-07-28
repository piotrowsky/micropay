package org.micropay.domain.transfer.validation;

public class AccountRelationshipException extends RuntimeException {
    AccountRelationshipException(String message) {
        super(message);
    }
}
