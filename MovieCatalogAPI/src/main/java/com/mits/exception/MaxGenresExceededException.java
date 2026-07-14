package com.mits.exception;

public class MaxGenresExceededException extends RuntimeException {

    public MaxGenresExceededException(String message) {
        super(message);
    }

}