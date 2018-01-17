package com.example.wan.myjingdong.model;

import android.os.Handler;
import android.os.Looper;

import com.example.wan.myjingdong.bean.DetailBean;
import com.example.wan.myjingdong.callback.OnNetListener;
import com.example.wan.myjingdong.http.OKHttpUntils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wan on 2018/1/9.
 */

public class DetailModel implements DetailFragmentService {
    private String path="http://120.27.23.105/product/getProductDetail";
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void getData(Map<String, String> map, final OnNetListener<DetailBean> onNetListener) {
        OKHttpUntils.getOkHttpUntils().doPost(path, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final DetailBean detailBean = new Gson().fromJson(string, DetailBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(detailBean);
                    }
                });
            }
        });
    }


    /**
     * Created by wan on 2018/1/9.
     */

    public static interface DetailFragmentService {
        void getData(Map<String, String> map, OnNetListener<DetailBean> onNetListener);

    }
}
