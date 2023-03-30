package com.autentia.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Requires(classes = {UserNotGroupException.class, ExceptionHandler.class})
public class UserNotGroupExceptionHandler implements ExceptionHandler<UserNotGroupException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, UserNotGroupException exception) {
        return HttpResponse.badRequest("User is not part of this group.");
    }
}
