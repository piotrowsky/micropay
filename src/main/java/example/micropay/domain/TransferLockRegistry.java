package example.micropay.domain;

import com.google.common.collect.ImmutableSet;

import javax.inject.Singleton;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Singleton
class TransferLockRegistry {
    private final Map<Set<String>, Lock> locks = new ConcurrentHashMap<>();

    void lockFor(Account account1, Account account2) {
        lockBy(key(account1, account2)).lock();
    }

    void unlockFor(Account account1, Account account2) {
        lockBy(key(account1, account2)).unlock();
    }

    private Lock lockBy(Set<String> key) {
        return locks.computeIfAbsent(key, k -> new ReentrantLock());
    }

    private Set<String> key(Account account1, Account account2) {
        return ImmutableSet.of(account1.getUniqueId(), account2.getUniqueId());
    }
}
