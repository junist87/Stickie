package com.ciaosgarage.stickie.beans.services.accountService;

public class CantAccessAccountException extends RuntimeException {
    public CantAccessAccountException() {
        super();
    }

    public CantAccessAccountException(String message) {
        super(message);
    }

    public CantAccessAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantAccessAccountException(Throwable cause) {
        super(cause);
    }

    protected CantAccessAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
