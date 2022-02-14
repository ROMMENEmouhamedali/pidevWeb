package com.epicoders.metier.ExceptionHandler;

public class ExceptionSqlError extends Exception{

    public ExceptionSqlError( String error){
        super(error);
    }
}
