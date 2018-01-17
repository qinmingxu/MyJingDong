package com.example.wan.myjingdong.presaenter;


import com.example.wan.myjingdong.LoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SDC on 2017/12/6.
 */

public class DetailModel {
    private static volatile DetailModel instance;
    private Retrofit retrofit;

    DetailModel(){

    }
    public static DetailModel getInstance(String baseUrl){
        if (instance==null){
            synchronized (DetailModel.class){
                if (null==instance){
                    instance = new DetailModel(baseUrl);

                }
            }
        }
        return instance;

    }
    private DetailModel(String baseUrl){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        retrofit = new Retrofit.Builder()
                .client(client)

                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public static DetailModel getInstance(){
        if (null == instance){
            return  getInstance("http://api.tianapi.com/");
        }
        return instance;
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }
}
