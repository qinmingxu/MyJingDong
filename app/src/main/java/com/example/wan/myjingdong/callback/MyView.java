package com.example.wan.myjingdong.callback;


import com.example.wan.myjingdong.bean.DataBean;

import java.util.List;

/**
 * Created by SDC on 2017/12/1.
 */

public interface MyView {
    void success(List<DataBean> news);

    void failed(Exception e);


}
