package com.example.wan.myjingdong.callback;


import com.example.wan.myjingdong.bean.MessageBean;

import java.util.List;

/**
 * Created by SDC on 2017/11/18.
 */

public interface CartsView {
    void success(List<MessageBean.DataBean> data);

    void failed(Exception e);

}
