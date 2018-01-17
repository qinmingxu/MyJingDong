package com.example.wan.myjingdong.callback;

/**
 * Created by wan on 2017/12/21.
 */

public interface OnNetListener<T> {
    void onSuccess(T t);
    void onFailure(Exception e);
}
