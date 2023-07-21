package com.areaonline.utils;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASE_URL = "https://www.areaonline.in/api/";
    public static final String BASE_URLPLA = "https://www.areaonline.in/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofit22 = null;
    private static int REQUEST_TIMEOUT = 200;
    private static OkHttpClient okHttpClient;

    public static Retrofit getClient() {
        if (okHttpClient == null)
            initOkHttp();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientPlace() {
        if (okHttpClient == null)
            initOkHttp();
        if (retrofit22 == null) {
            retrofit22 = new Retrofit.Builder()
                    .baseUrl(BASE_URLPLA)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit22;
    }
    private static void initOkHttp() {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);
        okHttpClient = httpClient.build();

    }



    public static Retrofit getservice() {
        if (okHttpClient == null)
            initOkHttp();


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.instagram.com/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
