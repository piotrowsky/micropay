package org.micropay.domain.account;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class AccountRepository {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    public Account getByUniqueId(String uniqueId) {
        return accounts.get(uniqueId);
    }

    public Account create(BigDecimal initialBalance) {
        Account account = new Account(initialBalance);
        accounts.put(account.getUniqueId(), account);
        return account;
    }

    public Collection<Account> getAll() {
        return accounts.values();
    }
}
