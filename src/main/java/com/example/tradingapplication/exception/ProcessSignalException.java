package com.example.tradingapplication.exception;

import java.io.Serial;

public class ProcessSignalException extends  RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ProcessSignalException(String message) {
        super(message);
    }
}
