package com.autentia.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Requires(classes = {BalanceNotFoundException.class, ExceptionHandler.class})
public class BalanceNotFoundExceptionHandler implements ExceptionHandler<BalanceNotFoundException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, BalanceNotFoundException exception) {
        return HttpResponse.badRequest("Balance Not Found.");
    }
}
