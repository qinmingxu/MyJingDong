package com.example.wan.myjingdong.presaenter;


import com.example.wan.myjingdong.bean.DetailBean;
import com.example.wan.myjingdong.callback.OnNetListener;
import com.example.wan.myjingdong.fragment.IDetailFragmentListener;
import com.example.wan.myjingdong.model.DetailFragmentService;
import com.example.wan.myjingdong.model.DetailModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan on 2018/1/11.
 */

public class Detail2Presenter {
    private IDetailFragmentListener iDetailFragmentListener;
    private final DetailFragmentService detailFragmentService;

    public Detail2Presenter(IDetailFragmentListener iDetailFragmentListener) {
        this.iDetailFragmentListener = iDetailFragmentListener;
        detailFragmentService = new DetailModel();
    }
    public void deach(){
        iDetailFragmentListener=null;
    }
    public void getData(String pid){
        Map<String,String> map = new HashMap<>();
        map.put("pid",pid);
        detailFragmentService.getData(map, new OnNetListener<DetailBean>() {
            @Override
            public void onSuccess(DetailBean detailBean) {
                if(iDetailFragmentListener!=null){
                    iDetailFragmentListener.show(detailBean);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    };

}
