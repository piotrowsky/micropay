package org.micropay.external.management;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class AccountCreationRequest {

    private final BigDecimal initialBalance;

    @JsonCreator
    public AccountCreationRequest(@JsonProperty("initialBalance") BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }
}
