package com.example.wan.myjingdong.presaenter;


import com.example.wan.myjingdong.bean.MessageBean;
import com.example.wan.myjingdong.callback.CartsCalllBack;
import com.example.wan.myjingdong.callback.CartsView;
import com.example.wan.myjingdong.http.CartsHttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SDC on 2017/11/18.
 */

public class CartsPresenter {
        private CartsView iv;
        public void attach(CartsView iv) {
            this.iv=iv;
        }
        public void getData(String uid){

            Map<String,String> map = new HashMap<>();
            map.put("uid",uid);

            CartsHttpUtils.getInstance().post("http://120.27.23.105/product/getCarts", map, new CartsCalllBack() {
                @Override
                public void onSuccess(Object o) {
                    if(o!=null){
                        MessageBean bean = (MessageBean) o;
                        List<MessageBean.DataBean> data = bean.getData();


                        iv.success(data);
                    }

                }

                @Override
                public void onFailed(Exception e) {
                    iv.failed(e);
                }
            }, MessageBean.class);
        }
    public void detach() {
        if(iv!=null){
            iv=null;
        }
    }
}
