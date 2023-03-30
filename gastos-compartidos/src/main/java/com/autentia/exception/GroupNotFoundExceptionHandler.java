package com.autentia.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Requires(classes = {GroupNotFoundException.class, ExceptionHandler.class})
public class GroupNotFoundExceptionHandler implements ExceptionHandler<GroupNotFoundException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, GroupNotFoundException exception) {
        return HttpResponse.badRequest("Group Not Found.");
    }
}
