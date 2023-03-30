package com.autentia.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Requires(classes = {BalanceNotInformedException.class, ExceptionHandler.class})
public class BalanceNotInformedExceptionHandler implements ExceptionHandler<BalanceNotInformedException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, BalanceNotInformedException exception) {
        return HttpResponse.badRequest("Balance Not Informed");
    }
}
