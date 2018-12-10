package com.gmd.test.http;

import com.gmd.common.http.BaseResponse;
import com.gmd.test.http.model.WeexUrlModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/12/10     zenglinggui       v1.0.0        create
 **/
public interface WeexService {

    @FormUrlEncoded
    @POST("weexUrl")
    Observable<BaseResponse<WeexUrlModel>> getWeexUrl(@Field("version") String version);

}
