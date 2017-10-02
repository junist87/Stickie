package com.ciaosgarage.stickie.beans.services.accountService;

public class TryLoginOverCountException extends RuntimeException {
    public TryLoginOverCountException() {
        super();
    }

    public TryLoginOverCountException(String message) {
        super(message);
    }

    public TryLoginOverCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public TryLoginOverCountException(Throwable cause) {
        super(cause);
    }

    protected TryLoginOverCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
