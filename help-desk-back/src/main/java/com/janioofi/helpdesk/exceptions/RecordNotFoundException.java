package com.janioofi.helpdesk.exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String msg) {
        super(msg);
    }
}
