package org.micropay.domain.transfer;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class TransferRepository {

    private final Map<String, Transfer> transfers = new ConcurrentHashMap<>();

    void save(Transfer transfer) {
        transfers.put(transfer.getUniqueId(), transfer);
    }

    public Collection<Transfer> getAll() {
        return transfers.values();
    }
}
