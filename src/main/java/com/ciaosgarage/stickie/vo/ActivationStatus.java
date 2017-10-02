package com.ciaosgarage.stickie.vo;

public enum ActivationStatus {
    WORIKING(1), DELETED(2);

    private Integer intValue;

    ActivationStatus(Integer intValue) {
        this.intValue = intValue;
    }

    public static ActivationStatus getActivationStatus(int value) {
        switch (value) {
            case 1:
                return WORIKING;
            case 2:
                return DELETED;
            default:
                throw new RuntimeException("Invalid Value : " + value);
        }
    }

    public static int getIntValue(ActivationStatus status) {
        switch (status) {
            case WORIKING: return 1;
            case DELETED: return 2;
            default:
                throw new RuntimeException("Invalid Value : " + status);
        }
    }

}
