package com.example.wan.myjingdong.presaenter;


import com.example.wan.myjingdong.bean.DataBean;
import com.example.wan.myjingdong.bean.JavasBean;
import com.example.wan.myjingdong.callback.MyCallBack;
import com.example.wan.myjingdong.callback.MyView;
import com.example.wan.myjingdong.http.OkHttpUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by SDC on 2017/12/1.
 */

public class MyPresenter {
    private MyView inv;

    public void attachView(MyView inv) {
        this.inv = inv;
    }
    public void getData() {

        OkHttpUtils.getInstance().post("http://120.27.23.105/product/getCatagory",  new MyCallBack() {


            @Override
            public void onSuccess( Object o) {
                JavasBean bean = (JavasBean) o;
                if (bean != null) {
                    List<DataBean> data = bean.getData();
                    inv.success( data);
                }
            }

            @Override
            public void onFailed(IOException e) {
                inv.failed(e);
            }
        }, JavasBean.class);
    }


    public void detachView() {
        if (inv != null) {
            inv = null;
        }

    }
}
