package org.micropay.domain.transfer;

import java.math.BigDecimal;

public class PersistableTransfer implements Transfer {

    private final TransferRepository transferRepository;
    private final Transfer delegatingTransfer;

    PersistableTransfer(TransferRepository transferRepository, Transfer delegatingTransfer) {
        this.transferRepository = transferRepository;
        this.delegatingTransfer = delegatingTransfer;
    }

    @Override
    public void execute() {
        delegatingTransfer.execute();
        transferRepository.save(delegatingTransfer);
    }

    @Override
    public String getUniqueId() {
        return delegatingTransfer.getUniqueId();
    }

    @Override
    public String getSourceAccountUniqueId() {
        return delegatingTransfer.getSourceAccountUniqueId();
    }

    @Override
    public String getDestinationAccountUniqueId() {
        return delegatingTransfer.getDestinationAccountUniqueId();
    }

    @Override
    public BigDecimal getAmount() {
        return delegatingTransfer.getAmount();
    }
}
