package com.example.wan.myjingdong.model;

import com.example.wan.myjingdong.bean.Orderbean;
import com.example.wan.myjingdong.callback.OnNetListener;

import java.util.Map;

/**
 * Created by wan on 2018/1/13.
 */

public interface OrderService {
    void getOrder(Map<String,String> map, OnNetListener<Orderbean> onNetListener);
}
