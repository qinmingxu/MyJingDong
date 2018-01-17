package com.example.wan.myjingdong.http;

import com.google.gson.Gson;

/**
 * Created by WuXirui
 * Create Time: 2017/11/9
 * Description:
 */

public class GsonUtils {
    private static Gson instance;

    private GsonUtils() {

    }

    public static Gson getInstance() {
        if (instance == null) {
            instance = new Gson();
        }
        return instance;
    }
}
