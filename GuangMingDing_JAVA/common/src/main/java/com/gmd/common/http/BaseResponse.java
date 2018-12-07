package com.gmd.common.http;

import android.graphics.Movie;

import java.util.List;

/**
 * 网络请求结果 基类
 */

public class BaseResponse<T> {
    public int status;
    public String message;

    public T data;

    //下面参数是为了请求豆瓣，测试

    public int count;
    public int start;
    public int total;
    public List<Movie> subjects;
    public String title;

    public boolean isSuccess() {
        return status == 200;
    }
}
