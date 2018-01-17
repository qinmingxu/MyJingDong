package com.example.wan.myjingdong.http;

import android.os.Handler;

import com.example.wan.myjingdong.bean.LoginBean;
import com.example.wan.myjingdong.callback.LoginCalllBack;
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
 * Created by SDC on 2017/11/23.
 */

public class LoginHttpUtils {
    private  static  volatile LoginHttpUtils instance;
    private LoginHttpUtils(){

    }
    private Handler handler = new Handler();
    public static LoginHttpUtils getInstance(){
        if(instance==null){
            synchronized (LoginHttpUtils.class){
                if(null==instance){
                    instance= new LoginHttpUtils();
                }
            }
        }
        return instance;
    }
    public  void post(String url, Map<String,String> map , final LoginCalllBack callBack, final Class cls){
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(new MyInterceptor())
                .build();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        FormBody body = builder.build();

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
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
            public void onResponse(final Call call, Response response) throws IOException {
               final String data = response.body().string().trim();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Object o = GsonUtils.getInstance().fromJson(data, LoginBean.class);
                        callBack.onSuccess(o);

                    }
                });
            }
        });
    }
}
