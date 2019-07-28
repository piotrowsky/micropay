package org.micropay.external.transfer.exception_handling;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.HttpResponse;
import org.micropay.domain.transfer.validation.AccountRelationshipException;
import org.micropay.external.transfer.TransferResponse;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {AccountRelationshipException.class, ExceptionHandler.class})
class AccountRelationshipExceptionHandler implements ExceptionHandler<AccountRelationshipException, HttpResponse<TransferResponse>> {

    @Override
    public HttpResponse<TransferResponse> handle(HttpRequest request, AccountRelationshipException exception) {
        return HttpResponse.badRequest(new TransferResponse(exception.getMessage()));
    }
}
