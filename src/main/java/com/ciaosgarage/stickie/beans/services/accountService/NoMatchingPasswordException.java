package com.ciaosgarage.stickie.beans.services.accountService;

public class NoMatchingPasswordException extends RuntimeException {
    public NoMatchingPasswordException() {
        super();
    }

    public NoMatchingPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMatchingPasswordException(Throwable cause) {
        super(cause);
    }

    protected NoMatchingPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoMatchingPasswordException(String s) {
    }
}
