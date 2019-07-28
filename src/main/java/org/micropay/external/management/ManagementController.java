package org.micropay.external.management;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.reactivex.Single;
import org.micropay.domain.account.Account;
import org.micropay.domain.account.AccountRepository;
import org.micropay.domain.transfer.TransferRepository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/management")
class ManagementController {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    @Inject
    ManagementController(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Put("/account/create")
    AccountCreationResponse createAccount(AccountCreationRequest request) {
        Account newAccount = accountRepository.create(request.getInitialBalance());
        return new AccountCreationResponse(newAccount.getUniqueId());
    }

    @Get("/account/list")
    Collection<AccountView> listAccounts() {
        return accountRepository
                .getAll()
                .stream()
                .map(AccountView::from)
                .collect(Collectors.toList());
    }

    @Get("/transfer/list")
    Collection<TransferView> listTransfers() {
        return transferRepository
                .getAll()
                .stream()
                .map(TransferView::from)
                .collect(Collectors.toList());
    }
}
