package com.example.wan.myjingdong.callback;


import com.example.wan.myjingdong.bean.HomeListBean;

import java.util.List;

/**
 * Created by WuXirui
 * Create Time: 2017/11/9
 * Description:
 */

public interface HomeListView {
    void suc(List<HomeListBean.DataBean> data);

    void fai(String tag, Exception e);

}
