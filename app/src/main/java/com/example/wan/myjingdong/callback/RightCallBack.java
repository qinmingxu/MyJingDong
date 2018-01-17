package com.example.wan.myjingdong.callback;

import java.io.IOException;

/**
 * Created by SDC on 2017/12/1.
 */

public interface RightCallBack {

    void onFailed(IOException e);

    void onSuccess(Object o);
}
