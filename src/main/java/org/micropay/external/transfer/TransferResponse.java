package org.micropay.external.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static org.micropay.external.transfer.TransferStatus.FAILED;
import static org.micropay.external.transfer.TransferStatus.SUCCESSFUL;

public class TransferResponse {

    public static final TransferResponse SUCCESS = new TransferResponse();

    private String failureMessage;
    private final TransferStatus transferStatus;

    @JsonCreator
    public TransferResponse(@JsonProperty("failureMessage") String failureMessage) {
        this.failureMessage = failureMessage;
        transferStatus = FAILED;
    }

    private TransferResponse() {
        transferStatus = SUCCESSFUL;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferResponse that = (TransferResponse) o;
        return Objects.equals(failureMessage, that.failureMessage) &&
                transferStatus == that.transferStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(failureMessage, transferStatus);
    }
}
