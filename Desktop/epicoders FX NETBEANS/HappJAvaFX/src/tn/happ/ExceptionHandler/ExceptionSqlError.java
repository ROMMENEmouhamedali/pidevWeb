package tn.happ.ExceptionHandler;

public class ExceptionSqlError extends Exception{

    public ExceptionSqlError( String error){
        super(error);
    }
}
