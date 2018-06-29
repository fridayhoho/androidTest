package com.android.samples.arch.componentsbasicsample.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetUtil {
    public static OkHttpClient getDebugOkClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
//                .cache(cache)
                .addInterceptor(loggingInterceptor)
//                .connectTimeout(mTimeOut, TimeUnit.SECONDS)
//                .readTimeout(mTimeOut, TimeUnit.SECONDS)
//                .writeTimeout(mTimeOut, TimeUnit.SECONDS)
                .build();
        return client;
    }
}
