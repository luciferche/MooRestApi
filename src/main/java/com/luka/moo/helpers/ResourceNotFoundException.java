package com.luka.moo.helpers;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
        System.out.println("Error: " + message);
    }
}
