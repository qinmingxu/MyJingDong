package com.example.wan.myjingdong.callback;


import com.example.wan.myjingdong.bean.SublistBean;

import java.util.List;


/**
 * Created by SDC on 2017/12/5.
 */

public interface SublistView {
    void onSuccess(List<SublistBean.DataBean> list);
    void onFailed(Exception e);


}
