package com.example.wan.myjingdong.presaenter;


import com.example.wan.myjingdong.bean.MessagesBean;
import com.example.wan.myjingdong.callback.RightCallBack;
import com.example.wan.myjingdong.callback.RightView;
import com.example.wan.myjingdong.http.RightHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SDC on 2017/12/1.
 */

public class RightPresenter {
    private RightView iv;

    public void attachView(RightView iv) {
        this.iv = iv;
    }
    public void getData(String cid) {

        Map<String,String > map =  new HashMap<>();
        map.put("cid",cid);
        RightHttpUtils.getInstance().post("http://120.27.23.105/product/getProductCatagory",map,  new RightCallBack() {


            @Override
            public void onSuccess( Object o) {
                MessagesBean bean = (MessagesBean) o;
                if (bean != null) {
                    List<MessagesBean.DataBean> data = bean.getData();
                    iv.show( data);
                }
            }

            @Override
            public void onFailed(IOException e) {
                iv.failed(e);
            }
        }, MessagesBean.class);
    }


    public void detachView() {
        if (iv != null) {
            iv = null;
        }

    }
}
