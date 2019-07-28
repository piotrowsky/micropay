package org.micropay.external.transfer.exception_handling;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import org.micropay.domain.transfer.validation.InvalidAmountException;
import org.micropay.external.transfer.TransferResponse;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {InvalidAmountException.class, ExceptionHandler.class})
class InvalidAmountExceptionHandler implements ExceptionHandler<InvalidAmountException, HttpResponse<TransferResponse>> {

    @Override
    public HttpResponse<TransferResponse> handle(HttpRequest request, InvalidAmountException exception) {
        return HttpResponse.badRequest(new TransferResponse(exception.getMessage()));
    }
}
