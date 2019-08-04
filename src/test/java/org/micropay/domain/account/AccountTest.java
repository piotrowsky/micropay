package org.micropay.domain.account;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountTest {

    private static final BigDecimal SOME_INITIAL_BALANCE = new BigDecimal(100);
    private static final BigDecimal SOME_BALANCE_BIGGER_THAN_INITIAL = new BigDecimal(101);
    private static final BigDecimal SOME_VALUE = new BigDecimal(40);
    private static final BigDecimal EXPECTED_VALUE_AFTER_DECREASE = new BigDecimal(60);
    private static final BigDecimal EXPECTED_VALUE_AFTER_INCREASE = new BigDecimal(140);

    @Test
    void shouldDecreaseBalance() {
        Account account = givenAccountWith(SOME_INITIAL_BALANCE);

        account.decreaseBalanceBy(SOME_VALUE);

        assertThat(account.getBalance()).isEqualTo(EXPECTED_VALUE_AFTER_DECREASE);
    }

    @Test
    void shouldRejectBalanceDecreaseWhenNotEnoughFunds() {
        Account account = givenAccountWith(SOME_INITIAL_BALANCE);

        assertThatThrownBy(() -> account.decreaseBalanceBy(SOME_BALANCE_BIGGER_THAN_INITIAL))
                .isInstanceOf(InsufficientBalanceException.class)
                .hasMessage(
                        "Insufficient funds for account: "
                        + account
                        + ". Requested amount: "
                        + SOME_BALANCE_BIGGER_THAN_INITIAL);
    }

    @Test
    void shouldIncreaseBalance() {
        Account account = givenAccountWith(SOME_INITIAL_BALANCE);

        account.increaseBalanceBy(SOME_VALUE);

        assertThat(account.getBalance()).isEqualTo(EXPECTED_VALUE_AFTER_INCREASE);
    }

    private static Account givenAccountWith(BigDecimal initialBalance) {
        return new Account(initialBalance);
    }
}
