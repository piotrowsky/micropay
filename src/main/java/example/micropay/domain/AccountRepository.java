package example.micropay.domain;

import javax.inject.Singleton;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class AccountRepository {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    public Account getByUniqueId(String uniqueId) {
        return accounts.get(uniqueId);
    }

    public void addAccount(Account newAccount) {
        accounts.put(newAccount.getUniqueId(), newAccount);
    }
}
