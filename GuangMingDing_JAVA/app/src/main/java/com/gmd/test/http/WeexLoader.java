package com.gmd.test.http;

import com.gmd.common.ApiConfig;
import com.gmd.common.http.ApiManager;
import com.gmd.common.http.ObjectLoader;
import com.gmd.common.http.PayLoad;
import com.gmd.common.http.interceptor.HeaderInterceptor;
import com.gmd.test.http.model.WeexUrlModel;

import io.reactivex.Observable;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/12/10     zenglinggui       v1.0.0        create
 **/
public class WeexLoader extends ObjectLoader {

    private WeexService service;

    public WeexLoader() {
        HeaderInterceptor.Builder builder = new HeaderInterceptor.Builder();
        builder.addHeaderParams("token", "asfdasdfwedas2141");

        service = ApiManager.getInstance().
                setBaseUrl(ApiConfig.BASE_URL).
                setCacheFileSize(20)
                .addInterceptor(builder.build())
                .create(WeexService.class);
    }

    public Observable<WeexUrlModel> getMoive(String version) {
        return observe(service.getWeexUrl(version))
                .map(new PayLoad<WeexUrlModel>());
    }
}
