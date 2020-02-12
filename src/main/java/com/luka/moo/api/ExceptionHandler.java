package com.luka.moo.api;

import com.luka.moo.helpers.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@ResponseBody
public class ExceptionHandler {

    //static class used as error wrapper
    public static class ErrorResponse {
        public final String message;

        ErrorResponse(String message) {
            this.message = message;
        }
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse entityNotFound(HttpServletRequest request, Exception e) {
        String msg = String.format("Exception while handling request %s", request.getRequestURL());
        System.out.println(msg + " - message - " + e.getMessage());
        return new ErrorResponse("Entity not found: " + e.getMessage());
    }
}
