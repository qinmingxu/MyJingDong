package com.example.wan.myjingdong.presaenter;


import com.example.wan.myjingdong.bean.LoginBean;
import com.example.wan.myjingdong.callback.CartsCalllBack;
import com.example.wan.myjingdong.callback.LoginView;
import com.example.wan.myjingdong.http.CartsHttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SDC on 2017/11/18.
 */

public class LoginPresenter {
        private LoginView iv;
        public void attach(LoginView iv) {
            this.iv=iv;
        }
        public void getData(String uid){

            Map<String,String> map = new HashMap<>();
            map.put("uid",uid+"");

            CartsHttpUtils.getInstance().post("http://120.27.23.105/user/getUserInfo", map, new CartsCalllBack() {
                @Override
                public void onSuccess(Object o) {
                    LoginBean bean = (LoginBean) o;
                    LoginBean.DataBean data = bean.getData();


                    iv.success(data);
                }

                @Override
                public void onFailed(Exception e) {
                    iv.failed(e);
                }
            }, LoginBean.class);
        }
}
