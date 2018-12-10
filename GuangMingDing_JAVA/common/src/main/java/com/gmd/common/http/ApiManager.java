package com.gmd.common.http;

import com.gmd.common.BaseApplication;
import com.gmd.common.http.interceptor.CacheInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {


    private static final int DEFAULT_MILLISECONDS = 15;//超时时间
    private static final int CACHE_FILE_SIZE = 10 * 1024 * 1024;//缓存文件大小


    private static volatile ApiManager instance;

    private OkHttpClient.Builder okHttpBuilder;
    private Retrofit.Builder retrofitBuilder;

    private ApiManager() {

        okHttpBuilder = new OkHttpClient.Builder().
                connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)//连接超时时间
                .writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)//写操作 超时时间
                .readTimeout(DEFAULT_MILLISECONDS, TimeUnit.SECONDS)//读操作超时时间
                .cache(initCacheFile(CACHE_FILE_SIZE))
                .addInterceptor(new CacheInterceptor());

        retrofitBuilder = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }


    public static ApiManager getInstance() {
        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager();
                }
            }
        }
        return instance;
    }

    public ApiManager setBaseUrl(String baseUrl) {
        retrofitBuilder.baseUrl(baseUrl);
        return this;
    }

    public ApiManager setCacheFileSize(int size) {
        initCacheFile(size);
        return this;
    }

    /**
     * 创建缓存文件
     *
     * @return
     */
    private Cache initCacheFile(int cacheSize) {
        try {
            File cacheFile = new File(BaseApplication.getInstance().getCacheDir() + "cache");
            if (!cacheFile.exists()) {
                cacheFile.createNewFile();
            }
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
        return retrofitBuilder.build().create(service);
    }

    /**
     * header HeaderInterceptor.Builder
     * cache CacheInterceptor
     *
     * @param interceptor
     * @return
     */
    public ApiManager addInterceptor(Interceptor interceptor) {
        okHttpBuilder.addInterceptor(interceptor);
        return this;
    }

}
