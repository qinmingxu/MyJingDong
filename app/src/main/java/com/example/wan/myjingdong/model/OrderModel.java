package com.example.wan.myjingdong.model;

import com.example.wan.myjingdong.bean.Orderbean;
import com.example.wan.myjingdong.callback.OnNetListener;
import com.example.wan.myjingdong.http.BaseObserver;
import com.example.wan.myjingdong.http.RetrofitManager;

import java.util.Map;

/**
 * Created by wan on 2018/1/13.
 */

public class OrderModel implements OrderService {
    @Override
    public void getOrder(Map<String, String> map, final OnNetListener<Orderbean> onNetListener) {

        RetrofitManager.post("product/getOrders", map, new BaseObserver<Orderbean>() {

            @Override
            public void success(Orderbean orderbean) {
                onNetListener.onSuccess(orderbean);
            }

            @Override
            public void failure(int code) {

            }
        });
    }
}
