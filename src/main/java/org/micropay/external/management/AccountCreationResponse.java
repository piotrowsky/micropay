package org.micropay.external.management;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountCreationResponse {

    private final String uniqueAccountId;

    @JsonCreator
    public AccountCreationResponse(@JsonProperty("uniqueAccountId") String uniqueAccountId) {
        this.uniqueAccountId = uniqueAccountId;
    }

    public String getUniqueAccountId() {
        return uniqueAccountId;
    }
}
