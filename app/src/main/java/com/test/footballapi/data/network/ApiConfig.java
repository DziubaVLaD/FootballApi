package com.test.footballapi.data.network;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Olga Liakhovich on 2/7/2019.
 */
public class ApiConfig {
    public static final String ENDPOINT = "https://www.football-data.org/";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final long DEFAULT_CONNECT_TIMEOUT = 10000;
    private static final long DEFAULT_WRITE_TIMEOUT = 10000;
    private static final long DEFAULT_READ_TIMEOUT = 10000;
    private static final String SERVER_VERSION = "1.0";

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(OBJECT_MAPPER))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build();
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor.Logger fileLogger = s -> Log.d("okhttp", s);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(fileLogger);
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder().build();

            boolean hasMultipart = request.headers().names().contains("multipart");

            logging.setLevel(hasMultipart ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.BODY);

            return chain.proceed(request);
        });
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("version", SERVER_VERSION);
            return chain.proceed(requestBuilder.build());
        });
        httpClient.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.addInterceptor(new TimeoutInterceptor());

        return httpClient.build();
    }
}
