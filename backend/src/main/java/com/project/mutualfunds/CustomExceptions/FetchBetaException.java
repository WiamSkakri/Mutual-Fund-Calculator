package com.project.mutualfunds.CustomExceptions;

public class FetchBetaException extends IllegalArgumentException{
    public FetchBetaException(String msg){
        super(msg);
    }
}
