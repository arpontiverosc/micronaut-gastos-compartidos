package com.autentia.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Requires(classes = {BadRequestException.class, ExceptionHandler.class})
public class BadRequestExceptionHandler implements ExceptionHandler<BadRequestException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, BadRequestException exception) {
        return HttpResponse.badRequest("Bad Request.");
    }
}
