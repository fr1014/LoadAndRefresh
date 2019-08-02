package com.fr.loadandrefresh.network;

import com.fr.loadandrefresh.network.request.Request;
import com.fr.loadandrefresh.utils.NetStatusUtil;
import com.fr.loadandrefresh.application.BaseApplication;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.fr.loadandrefresh.network.request.Request.BASE_URL;

public class RetrofitManager {
    private static RetrofitManager mInstance;
    private static Retrofit retrofit;
    private static volatile Request request = null;
    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 20;
    private static final int WRITE_TIMEOUT = 20;

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化必要对象和参数
     */
    public void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    private OkHttpClient createOkHttpClient() {
        Cache cache = new Cache(new File(BaseApplication.getContext().getCacheDir(), "httpCache"), 1024 * 1024 * 100);
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)  //连接超时时间阈值
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)   //数据获取时间阈值
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)  //写数据超时时间阈值
                .retryOnConnectionFailure(true)     //错误重连
                .cache(cache)
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .build();
    }

    //缓存策略
    private class HttpCacheInterceptor implements Interceptor {

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            okhttp3.Request request = chain.request();
            if (!NetStatusUtil.isConnected(BaseApplication.getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);

            if (NetStatusUtil.isConnected(BaseApplication.getContext())) {
                int maxAge = 60; // read from cache for 1 minute
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7; // tolerate 1-weeks stale
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }

    public static Request getRequest() {
        if (request == null) {
            synchronized (Request.class) {
                request = retrofit.create(Request.class);
            }
        }
        return request;
    }
}
