package com.example.wan.myjingdong.callback;

/**
 * Created by SDC on 2017/11/13.
 */

public interface HomeBottomCallBack {
    void onSuccess(String tag, Object o);

    void onFailed(String tag, Exception e);
}
