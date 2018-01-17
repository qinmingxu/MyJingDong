package com.example.wan.myjingdong.presaenter;


import com.example.wan.myjingdong.bean.HomeListBean;
import com.example.wan.myjingdong.bean.JsonRootBean;
import com.example.wan.myjingdong.callback.HomeBottomView;
import com.example.wan.myjingdong.callback.HomeListCallBack;
import com.example.wan.myjingdong.http.HomeListHttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SDC on 2017/11/13.
 */

public class HomeBottomPresenter {
    private HomeBottomView iv;

    public void attachView(HomeBottomView iv) {
        this.iv = iv;
    }

    public void getNews() {
        Map<String, String> map = new HashMap<>();

        HomeListHttpUtils.getInstance().getData("http://120.27.23.105/ad/getAd", new HomeListCallBack() {
            @Override
            public void onSuccess(String tag, Object o) {

                HomeListBean bean = (HomeListBean) o;
                List<HomeListBean.DataBean> list = bean.getData();

                iv.suc(list);
            }

            @Override
            public void onFailed(String tag, Exception e) {

            }
        }, JsonRootBean.class,"");

    }

    public void detachView() {
        if (iv != null) {
            iv = null;
        }

    }



}
