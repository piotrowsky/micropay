package org.micropay.domain.transfer;

import java.math.BigDecimal;

public interface Transfer {

    void execute();

    String getUniqueId();

    String getSourceAccountUniqueId();

    String getDestinationAccountUniqueId();

    BigDecimal getAmount();
}
