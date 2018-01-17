package com.example.wan.myjingdong.presaenter;


import com.example.wan.myjingdong.bean.DetailBean;
import com.example.wan.myjingdong.callback.DetailView;
import com.example.wan.myjingdong.callback.GitHubService;

import retrofit2.Retrofit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by SDC on 2017/12/6.
 */

public class DetailPresenter {
    private DetailView inv;
    private Subscription subscribe;
    private String baseUrl="http://120.27.23.105/product/";
    public void attachView(DetailView inv) {
        this.inv = inv;
    }

    public void getNews(String pid) {
        Retrofit retrofit = DetailModel.getInstance(baseUrl).getRetrofit();
        GitHubService service = retrofit.create(GitHubService.class);
        subscribe = service.detailData(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DetailBean>() {
                    @Override
                    public void call(DetailBean javaBean) {
                        DetailBean.DataBean data = javaBean.getData();

                        inv.success(data);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    public void detachView() {
        // 当Activity销毁的时候取消订阅时间，防止内存泄漏
        if (subscribe != null) {
            if (subscribe.isUnsubscribed()) {
                subscribe.unsubscribe();
            }
        }
        if (inv != null) {
            inv = null;
        }
    }
}
