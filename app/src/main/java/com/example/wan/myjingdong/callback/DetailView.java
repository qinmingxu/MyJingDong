package com.example.wan.myjingdong.callback;


import com.example.wan.myjingdong.bean.DetailBean;

/**
 * Created by SDC on 2017/12/1.
 */

public interface DetailView {

    void failed(Exception e);

    void success(DetailBean.DataBean data);
}
