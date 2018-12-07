package com.gmd.common.http;

import android.content.Context;

import com.gmd.common.BaseApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceManager {

    private static final int DEFAULT_TIME_OUT = 15;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 20;

    private Retrofit mRetrofit;
    private OkHttpClient.Builder okHttpBuilder;

    private int cacheStrategy;










    private void initmRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .client(initOkHttp().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * 初始化okhttp对象
     *
     * @return
     */
    private OkHttpClient.Builder initOkHttp() {
//        CacheInterceptor cacheInterceptor=new CacheInterceptor(cacheStrategy, context);
        okHttpBuilder = new OkHttpClient.Builder().
                connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//连接超时时间
                .writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)//写操作 超时时间
                .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)//读操作超时时间
                .cache(createCacheFile())
//                .addInterceptor(cacheInterceptor)
                .addInterceptor(getHeader());//设置header拦截
        return okHttpBuilder;
    }

    /**
     * 创建缓存文件
     *
     * @return
     */
    private Cache createCacheFile() {
        try {
            File cacheFile = new File(BaseApplication.getInstance().getCacheDir() + "cache");
            if (!cacheFile.exists()) {
                cacheFile.createNewFile();
            }
            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(cacheFile, cacheSize);
            return cache;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    /**
     * 添加公共参数拦截器
     *
     * @return
     */
    private HeaderInterceptor getHeader() {
        HeaderInterceptor commonInterceptor = new HeaderInterceptor.Builder()
                .addHeaderParams("paltform", "android")
                .addHeaderParams("userToken", "1234343434dfdfd3434")
                .addHeaderParams("userId", "123445")
                .build();
        return commonInterceptor;
    }

}
