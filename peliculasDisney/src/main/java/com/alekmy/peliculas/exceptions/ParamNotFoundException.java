package com.alekmy.peliculas.exceptions;

public class ParamNotFoundException extends RuntimeException{
   
    private static final String Description= "Param not found";
    
    public ParamNotFoundException (String details){
        super(Description + ": " + details);
    }
    
}
