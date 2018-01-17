package com.example.wan.myjingdong.callback;


import com.example.wan.myjingdong.bean.SublistBean;

/**
 * Created by SDC on 2017/12/5.
 */

public interface SublistCallback {
    void success(SublistBean body);
    void failed(Exception e);
}
