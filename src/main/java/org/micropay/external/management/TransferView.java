package org.micropay.external.management;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.micropay.domain.transfer.Transfer;

import java.math.BigDecimal;

public class TransferView {

    private final String uniqueId;
    private final String sourceAccountUniqueId;
    private final String destinationAccountUniqueId;
    private final BigDecimal amount;

    @JsonCreator
    // 4 params constructor, bit ugly but builder is overkill
    public TransferView(
            @JsonProperty("uniqueId") String uniqueId,
            @JsonProperty("sourceAccountUniqueId") String sourceAccountUniqueId,
            @JsonProperty("destinationAccountUniqueId") String destinationAccountUniqueId,
            @JsonProperty("amount") BigDecimal amount) {
        this.uniqueId = uniqueId;
        this.sourceAccountUniqueId = sourceAccountUniqueId;
        this.destinationAccountUniqueId = destinationAccountUniqueId;
        this.amount = amount;
    }

    static TransferView from(Transfer transfer) {
        return new TransferView(
                transfer.getUniqueId(),
                transfer.getSourceAccountUniqueId(),
                transfer.getDestinationAccountUniqueId(),
                transfer.getAmount()
        );
    }

    public String getUniqueId() {
        return uniqueId;
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
