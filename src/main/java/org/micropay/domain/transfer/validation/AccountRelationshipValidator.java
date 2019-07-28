package org.micropay.domain.transfer.validation;

import javax.inject.Singleton;

@Singleton
class AccountRelationshipValidator {

     void validate(String sourceAccountUniqueId, String destinationAccountUniqueId) {
        if (sourceAccountUniqueId.equals(destinationAccountUniqueId)) {
            throw new AccountRelationshipException(
                    "Cannot execute transfer between the same account. Affected account id: " + sourceAccountUniqueId);
        }
    }
}
