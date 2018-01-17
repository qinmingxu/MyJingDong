package com.example.wan.myjingdong.model;


import com.example.wan.myjingdong.bean.DetailBean;
import com.example.wan.myjingdong.callback.OnNetListener;

import java.util.Map;

/**
 * Created by wan on 2018/1/9.
 */

public interface DetailFragmentService {
    void getData(Map<String, String> map, OnNetListener<DetailBean> onNetListener);

}
