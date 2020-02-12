package com.luka.moo.helpers;


public class RestPreconditions {
    public static <T> T checkNotNull(T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException("Resource not found");
        }
        return resource;
    }
}
