package org.micropay.domain.transfer.validation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;

@Singleton
public class TransferCorrectnessValidator {

    private final AccountRelationshipValidator accountRelationshipValidator;
    private final AmountValidator amountValidator;

    @Inject
    TransferCorrectnessValidator(AccountRelationshipValidator accountRelationshipValidator, AmountValidator amountValidator) {
        this.accountRelationshipValidator = accountRelationshipValidator;
        this.amountValidator = amountValidator;
    }

    public void validate(String sourceAccountUniqueId, String destinationAccountUniqueId, BigDecimal amount) {
        accountRelationshipValidator.validate(sourceAccountUniqueId, destinationAccountUniqueId);
        amountValidator.validate(amount);
    }
}
