package com.example.wan.myjingdong.callback;


import com.example.wan.myjingdong.bean.LoginBean;

/**
 * Created by SDC on 2017/11/18.
 */

public interface LoginView {
    void success(LoginBean.DataBean data);

    void failed(Exception e);

}
