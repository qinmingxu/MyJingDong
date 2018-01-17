package com.example.wan.myjingdong.presaenter;


import com.example.wan.myjingdong.bean.SublistBean;
import com.example.wan.myjingdong.callback.SublistCallback;
import com.example.wan.myjingdong.callback.SublistView;

import java.util.List;

/**
 * Created by SDC on 2017/12/5.
 */

public class SublistPresenter {
    private SublistView iv;

    public SublistPresenter(SublistView iv) {
        this.iv = iv;
    }
    public void getData( String url,String pscid, int page, int sort){
        SublistModel.getInstance().get(url,pscid, page,sort, new SublistCallback() {
            @Override
            public void success(SublistBean body) {
                List<SublistBean.DataBean> newslist = body.getData();
                iv.onSuccess(newslist);
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }
}
