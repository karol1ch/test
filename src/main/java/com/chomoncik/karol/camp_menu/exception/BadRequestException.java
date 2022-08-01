package com.chomoncik.karol.camp_menu.exception;

public class BadRequestException extends RuntimeException{
    private static final long resourceId = 1L;

    public BadRequestException(String message) {
        super(message);
    }
}