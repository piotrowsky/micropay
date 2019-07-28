package org.micropay.external.transfer;

import org.micropay.domain.transfer.Transfer;
import org.micropay.domain.transfer.TransferFactory;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;

import static org.micropay.external.transfer.TransferResponse.SUCCESS;

@Controller("/transfer")
class TransferController {

    private final TransferFactory transferFactory;

    @Inject
    TransferController(TransferFactory transferFactory) {
        this.transferFactory = transferFactory;
    }

    @Post("/execute")
    TransferResponse execute(TransferRequest request) {
        Transfer transfer = transferFactory.transferFor(
                request.getSourceAccountUniqueId(),
                request.getDestinationAccountUniqueId(),
                request.getAmount()
        );
        transfer.execute();
        return SUCCESS;
    }
}
