package com.ciaosgarage.stickie.beans.services.accountService;

public class NoExistColumnValueException extends RuntimeException {
    public NoExistColumnValueException() {
    }

    public NoExistColumnValueException(String s) {
    }

    public NoExistColumnValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExistColumnValueException(Throwable cause) {
        super(cause);
    }

    public NoExistColumnValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
