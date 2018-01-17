package com.example.wan.myjingdong.http;

import android.os.Handler;
import android.text.TextUtils;

import com.example.wan.myjingdong.callback.CartsCalllBack;
import com.example.wan.myjingdong.interceptor.MyInterceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SDC on 2017/11/18.
 */

public class CartsHttpUtils {
    private  static  volatile CartsHttpUtils instance;
    private CartsHttpUtils(){

    }
    private Handler handler = new Handler();
    public static CartsHttpUtils getInstance(){
        if(instance==null){
            synchronized (CartsHttpUtils.class){
                if(null==instance){
                    instance= new CartsHttpUtils();
                }
            }
        }
        return instance;
    }
    //    String url="https://www.zhaoapi.cn/product/getCarts?uid=71";
    public  void post(String url, Map<String,String> map , final CartsCalllBack callBack, final Class cls){
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(new MyInterceptor())
                .build();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

//        FormBody body = ;

        final Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailed(e);

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string().trim();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Object o;
                        if (TextUtils.isEmpty(data)) {
                            o = null;
                        } else {
                            o = GsonUtils.getInstance().fromJson(data, cls);
                            callBack.onSuccess(o);

                        }
//                        Object o = GsonUtils.getInstance().fromJson(data,cls);
                    }
                });

            }
        });
    }
}
