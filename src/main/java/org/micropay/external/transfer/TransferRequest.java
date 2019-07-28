package org.micropay.external.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransferRequest {

    private final String sourceAccountUniqueId;
    private final String destinationAccountUniqueId;
    private final BigDecimal amount;

    @JsonCreator
    public TransferRequest(
            @JsonProperty("sourceAccountUniqueId") String sourceAccountUniqueId,
            @JsonProperty("destinationAccountUniqueId") String destinationAccountUniqueId,
            @JsonProperty("amount") BigDecimal amount) {
        this.sourceAccountUniqueId = sourceAccountUniqueId;
        this.destinationAccountUniqueId = destinationAccountUniqueId;
        this.amount = amount;
    }

    public String getSourceAccountUniqueId() {
        return sourceAccountUniqueId;
    }

    public String getDestinationAccountUniqueId() {
        return destinationAccountUniqueId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
