package org.micropay.transfer;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;
import org.micropay.external.management.AccountCreationRequest;
import org.micropay.external.management.AccountCreationResponse;
import org.micropay.external.management.AccountView;

import java.util.Collection;

@Client("/")
public interface ManagementClient {

    @Put("/management/account/create")
    Single<AccountCreationResponse> createAccount(AccountCreationRequest request);

    @Get("/management/account/list")
    Single<Collection<AccountView>> listAccounts();
}
