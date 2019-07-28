package org.micropay.transfer;

import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;
import org.micropay.external.transfer.TransferRequest;
import org.micropay.external.transfer.TransferResponse;

@Client("/")
public interface TransferClient {

    @Post("/transfer/execute")
    Single<TransferResponse> execute(TransferRequest request);
}
