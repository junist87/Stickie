package com.ciaosgarage.stickie.vo.account;

public enum AuthorizationStatus {
    NONE(1), PASS(2);

    private int intValue;

    AuthorizationStatus(int intValue) {
        this.intValue = intValue;
    }

    public static AuthorizationStatus getStatus(int value) {
        switch (value ) {
            case 1: return NONE;
            case 2: return PASS;
            default: return null;
        }
    }
    public static int getIntValue(AuthorizationStatus status) {
        switch (status) {
            case NONE: return 1;
            case PASS: return 2;
            default: return 0;
        }
    }
}
