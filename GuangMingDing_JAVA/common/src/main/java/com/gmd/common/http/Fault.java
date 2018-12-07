package com.gmd.common.http;

/**
 * 异常处理类，将异常包装成一个 Fault ,抛给上层统一处理
 */

public class Fault extends Exception {
    private int errorCode;

    public Fault(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
