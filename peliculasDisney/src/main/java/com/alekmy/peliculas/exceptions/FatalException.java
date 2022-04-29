package com.alekmy.peliculas.exceptions;

public class FatalException extends RuntimeException{
    
    private static final String DESCRIPTION = "Fatal error!";
    
    public FatalException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
