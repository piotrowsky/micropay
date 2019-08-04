package org.micropay.domain.account;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AccountRepositoryTest {

    private static final BigDecimal SOME_INITIAL_BALANCE = new BigDecimal(200);

    private final AccountRepository repository = new AccountRepository();

    @Test
    void shouldGetAccountById() {
        Account account1 = givenAccount();
        givenAccount();

        Account result = repository.getByUniqueId(account1.getUniqueId());

        assertThat(result).isEqualTo(account1);
    }

    private Account givenAccount() {
        return repository.create(SOME_INITIAL_BALANCE);
    }

}
