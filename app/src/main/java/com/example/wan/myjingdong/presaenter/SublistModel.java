package com.example.wan.myjingdong.presaenter;


import com.example.wan.myjingdong.bean.SublistBean;
import com.example.wan.myjingdong.callback.GitHubService;
import com.example.wan.myjingdong.callback.SublistCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SDC on 2017/12/5.
 */

public class SublistModel {
    private Call<SublistBean> repos;
    private GitHubService service;
    private static volatile  SublistModel instance;
    private SublistModel(){

    }

    public static SublistModel getInstance(){
        if(instance==null){
            synchronized (SublistModel.class){
                if(null==instance){
                    instance = new SublistModel();
                }
            }
        }
        return instance;
    }
    public void get( String url,String pscid, int page, int sort, final SublistCallback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(GitHubService.class);
        repos = service.search("getProducts",pscid,page+"",sort+"");


        repos.enqueue(new Callback<SublistBean>() {
            @Override
            public void onResponse(Call<SublistBean> call, Response<SublistBean> response) {
//                Log.e("APP",response.body().getNewslist().toString());

                SublistBean body = response.body();
                callback.success(body);

            }
            @Override
            public void onFailure(Call<SublistBean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
