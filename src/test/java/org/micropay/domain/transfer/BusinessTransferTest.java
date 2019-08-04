package org.micropay.domain.transfer;

import org.junit.jupiter.api.Test;
import org.micropay.domain.account.Account;
import org.micropay.domain.account.InsufficientBalanceException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BusinessTransferTest {

    private static final BigDecimal SOME_INITIAL_BALANCE = new BigDecimal(100);
    private static final BigDecimal SOME_OTHER_INITIAL_BALANCE = new BigDecimal(200);
    private static final BigDecimal SOME_BALANCE_BIGGER_THAN_INITIAL = new BigDecimal(101);
    private static final BigDecimal SOME_TRANSFER_AMOUNT = new BigDecimal(50);

    @Test
    void shouldExecuteTransferBetweenAccounts() {
        Account src = givenAccountWith(SOME_INITIAL_BALANCE);
        Account dst = givenAccountWith(SOME_OTHER_INITIAL_BALANCE);

        new BusinessTransfer(src, dst, SOME_TRANSFER_AMOUNT).execute();

        assertThat(src.getBalance()).isEqualTo(new BigDecimal(50));
        assertThat(dst.getBalance()).isEqualTo(new BigDecimal(250));
    }

    @Test
    void shouldRejectTransferWhenNotEnoughFundsOnSourceAccount() {
        Account src = givenAccountWith(SOME_INITIAL_BALANCE);

        assertThatThrownBy(() -> new BusinessTransfer(src, null, SOME_BALANCE_BIGGER_THAN_INITIAL).execute())
                .isInstanceOf(TransferFailureException.class)
                .hasCauseInstanceOf(InsufficientBalanceException.class);
    }

    @Test
    void shouldGetSourceAccountId() {
        Account src = givenSomeAccount();

        BusinessTransfer businessTransfer = new BusinessTransfer(src, null, null);

        assertThat(businessTransfer.getSourceAccountUniqueId()).isEqualTo(src.getUniqueId());
    }

    @Test
    void shouldGetDestinationAccountId() {
        Account dst = givenSomeAccount();

        BusinessTransfer businessTransfer = new BusinessTransfer(null, dst, null);

        assertThat(businessTransfer.getDestinationAccountUniqueId()).isEqualTo(dst.getUniqueId());
    }

    @Test
    void shouldGetAmount() {
        BusinessTransfer businessTransfer = new BusinessTransfer(null, null, SOME_TRANSFER_AMOUNT);

        assertThat(businessTransfer.getAmount()).isEqualTo(SOME_TRANSFER_AMOUNT);
    }

    private static Account givenAccountWith(BigDecimal initialBalance) {
        return new Account(initialBalance);
    }

    private static Account givenSomeAccount() {
        return givenAccountWith(BigDecimal.ZERO);
    }
}