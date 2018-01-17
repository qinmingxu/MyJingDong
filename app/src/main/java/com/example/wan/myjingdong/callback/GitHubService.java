package com.example.wan.myjingdong.callback;


import com.example.wan.myjingdong.bean.DetailBean;
import com.example.wan.myjingdong.bean.SublistBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by SDC on 2017/12/5.
 */

public interface GitHubService {
//        http://120.27.23.105/product/getProducts?pscid=1&page=1&sort=0
//        http://120.27.23.105/product/getProductDetail?pid=1
        @POST("{getProducts}")
        Call<SublistBean> search(@Path("getProducts") String getProducts, @Query("pscid") String key, @Query("page") String num, @Query("sort") String sort);
        @GET("getProductDetail")
        Observable<DetailBean> detailData(@Query("pid") String pid);
}
