package main.Exceptions;

public class EmptyQueueOnCashException extends Exception {
    public EmptyQueueOnCashException(String message){
        super(message);
    }
}
