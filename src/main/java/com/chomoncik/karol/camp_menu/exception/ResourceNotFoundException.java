package com.chomoncik.karol.camp_menu.exception;

public class ResourceNotFoundException extends RuntimeException{
    private static final long resourceId = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
