package com.ciaosgarage.stickie.beans.services.accountService;

public class CantRemoveRecordException extends RuntimeException {
    public CantRemoveRecordException() {
        super();
    }

    public CantRemoveRecordException(String message) {
        super(message);
    }

    public CantRemoveRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantRemoveRecordException(Throwable cause) {
        super(cause);
    }

    protected CantRemoveRecordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
