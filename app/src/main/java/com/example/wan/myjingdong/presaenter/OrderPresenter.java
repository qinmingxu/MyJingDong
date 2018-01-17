package com.example.wan.myjingdong.presaenter;

import com.example.wan.myjingdong.bean.Orderbean;
import com.example.wan.myjingdong.callback.IOrderActivityListener;
import com.example.wan.myjingdong.callback.OnNetListener;
import com.example.wan.myjingdong.model.OrderModel;
import com.example.wan.myjingdong.model.OrderService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan on 2018/1/13.
 */

public class OrderPresenter {
    private IOrderActivityListener iOrderActivityListener;
    private final OrderService orderService;

    public OrderPresenter(IOrderActivityListener iOrderActivityListener) {
        this.iOrderActivityListener = iOrderActivityListener;
        orderService = new OrderModel();
    }
    public void getOrder(String status){
        Map<String,String> map = new HashMap<>();
        map.put("uid","71");
        map.put("status",status);
        orderService.getOrder(map, new OnNetListener<Orderbean>() {
            @Override
            public void onSuccess(Orderbean orderbean) {
                if(iOrderActivityListener!=null){
                    iOrderActivityListener.show(orderbean);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
