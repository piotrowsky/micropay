package org.micropay.transfer;

import io.micronaut.test.annotation.MicronautTest;
import io.reactivex.Single;
import org.junit.jupiter.api.Test;
import org.micropay.external.management.AccountCreationRequest;
import org.micropay.external.management.AccountCreationResponse;
import org.micropay.external.management.AccountView;
import org.micropay.external.transfer.TransferRequest;
import org.micropay.external.transfer.TransferResponse;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.micropay.external.transfer.TransferResponse.SUCCESS;

@MicronautTest
class TransferSystemTest {

    private static final int SOME_FIRST_BALANCE = 100;
    private static final int SOME_SECOND_BALANCE = 200;
    private static final int EXPECTED_FIRST_BALANCE = 0;
    private static final int EXPECTED_SECOND_BALANCE = 300;
    private static final int SOME_TRANSFER_AMOUNT = 100;

    @Inject private TransferClient transferClient;
    @Inject private ManagementClient managementClient;

    @Test
    void shouldExecuteTransferBetweenAccounts() {
        String firstAccountId = givenAccountWith(SOME_FIRST_BALANCE);
        String secondAccountId = givenAccountWith(SOME_SECOND_BALANCE);
        TransferRequest transferRequest = givenTransferRequestWith(firstAccountId, secondAccountId, SOME_TRANSFER_AMOUNT);

        TransferResponse transferResponse = transferClient.execute(transferRequest).blockingGet();

        assertThat(transferResponse).isEqualTo(SUCCESS);
        thenAccountsStateIsFollowing(
                accountWith(firstAccountId, EXPECTED_FIRST_BALANCE),
                accountWith(secondAccountId, EXPECTED_SECOND_BALANCE)
        );
    }

    private String givenAccountWith(int amount) {
        Single<AccountCreationResponse> response = managementClient.createAccount(new AccountCreationRequest(new BigDecimal(amount)));
        AccountCreationResponse creationResponse = response.blockingGet();
        return creationResponse.getUniqueAccountId();
    }

    private static TransferRequest givenTransferRequestWith(String firstAccountId, String secondAccountId, int amount) {
        return new TransferRequest(firstAccountId, secondAccountId, new BigDecimal(amount));
    }

    private static AccountView accountWith(String uniqueId, int balance) {
        return new AccountView(uniqueId, new BigDecimal(balance));
    }

    private void thenAccountsStateIsFollowing(AccountView... accounts) {
        Collection<AccountView> accountsViews = managementClient.listAccounts().blockingGet();
        assertThat(accountsViews).containsExactlyInAnyOrder(accounts);
    }
}
