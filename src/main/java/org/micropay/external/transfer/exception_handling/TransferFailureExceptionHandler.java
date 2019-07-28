package org.micropay.external.transfer.exception_handling;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import org.micropay.domain.transfer.TransferFailureException;
import org.micropay.external.transfer.TransferResponse;

import javax.inject.Singleton;

import static io.micronaut.http.HttpStatus.PRECONDITION_FAILED;

@Produces
@Singleton
@Requires(classes = {TransferFailureException.class, ExceptionHandler.class})
class TransferFailureExceptionHandler implements ExceptionHandler<TransferFailureException, HttpResponse>{

    @Override
    public HttpResponse handle(HttpRequest request, TransferFailureException exception) {
        return HttpResponse.status(PRECONDITION_FAILED).body(new TransferResponse(exception.getMessage()));
    }
}
