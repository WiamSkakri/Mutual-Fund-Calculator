package com.project.mutualfunds.CustomExceptions;

public class MutualFundsRequestsException extends IllegalArgumentException{
    public MutualFundsRequestsException(String msg){
        super(msg);
    }
}
