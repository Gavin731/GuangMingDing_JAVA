package com.gmd.common.http;

/**
 * 网络请求结果 基类
 */

public class BaseResponse<T> {
    public int status;
    public String message;

    public T data;
}
