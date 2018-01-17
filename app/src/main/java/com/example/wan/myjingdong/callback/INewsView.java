package com.example.wan.myjingdong.callback;


import com.example.wan.myjingdong.bean.Data;
import com.example.wan.myjingdong.bean.DataList;

import java.util.List;

/**
 * Created by WuXirui
 * Create Time: 2017/11/9
 * Description:
 */

public interface INewsView {
    void success(List<DataList> list, List<DataList> data, List<Data> datas);

    void failed(String tag, Exception e);

//    void success(List<DataList> list);
}
