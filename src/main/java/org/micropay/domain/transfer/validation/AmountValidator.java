package org.micropay.domain.transfer.validation;

import javax.inject.Singleton;
import java.math.BigDecimal;

@Singleton
class AmountValidator {

    void validate(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be positive, but was: " + amount);
        }
    }
}
