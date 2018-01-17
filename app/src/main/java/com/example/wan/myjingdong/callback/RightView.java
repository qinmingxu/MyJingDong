package com.example.wan.myjingdong.callback;


import com.example.wan.myjingdong.bean.MessagesBean;

import java.util.List;

/**
 * Created by SDC on 2017/12/1.
 */

public interface RightView {
    void show(List<MessagesBean.DataBean> news);

    void failed(Exception e);
}
