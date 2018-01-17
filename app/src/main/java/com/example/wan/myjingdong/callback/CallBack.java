package com.example.wan.myjingdong.callback;

/**
 * Created by WuXirui
 * Create Time: 2017/11/9
 * Description:
 */

public interface CallBack {
    void onSuccess(String tag, Object o);

    void onFailed(String tag, Exception e);
}
