package com.OTH.KoenigsBank.exception;

public class KontoNotFoundException extends RuntimeException {

    public KontoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public KontoNotFoundException(String message) {
        super(message);
    }

    public KontoNotFoundException(Throwable cause) {
        super(cause);
    }
}
