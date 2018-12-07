package com.gmd.common.http;


import com.blankj.utilcode.util.LogUtils;
import com.gmd.common.datamodel.GsonConverter;

import io.reactivex.functions.Function;

/**
 * 剥离 最终数据
 */
public class PayLoad<T> implements Function<BaseResponse<T>, T> {

    @Override
    public T apply(BaseResponse<T> tBaseResponse) throws Exception {
        LogUtils.e("----tBaseResponse:" + GsonConverter.toJson(tBaseResponse));
        if (tBaseResponse.status != 0) {
            throw new Fault(tBaseResponse.status, tBaseResponse.message);
        }
        return tBaseResponse.data;
    }
}
