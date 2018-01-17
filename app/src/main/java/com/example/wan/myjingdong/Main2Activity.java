package com.example.wan.myjingdong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wan.myjingdong.adapter.SublistAdapter;
import com.example.wan.myjingdong.bean.SublistBean;
import com.example.wan.myjingdong.callback.GitHubService;
import com.example.wan.myjingdong.callback.SublistView;
import com.example.wan.myjingdong.presaenter.SublistPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity implements XRecyclerView.LoadingListener, SublistView, View.OnClickListener {

    @BindView(R.id.iv_kind_back)
    ImageView mIvKindBack;
    @BindView(R.id.iv_kind_type)
    ImageView mIvKindType;
    @BindView(R.id.tv_shaixuan)
    TextView mTvShaixuan;
    @BindView(R.id.btn_jingdong)
    Button mBtnJingdong;
    @BindView(R.id.btn_new_shop)
    Button mBtnNewShop;
    @BindView(R.id.btn_grade)
    Button mBtnGrade;
    @BindView(R.id.btn_caizhi)
    Button mBtnCaizhi;
    @BindView(R.id.rcv_kind)
    XRecyclerView mRcvKind;
    private List<SublistBean.DataBean> list = new ArrayList<>();
    private String url = "http://120.27.23.105/product/";
    private int page = 1;
    private int sort = 0;
    private GitHubService service;
    private SublistAdapter adapter;
    private String pscid;
    private boolean isFresh = true;
    private boolean flag = true;
    private static final String TAG = "Main2Activity";
    /**
     * 综合
     */
    private RadioButton mZonghe;
    /**
     * 销量
     */
    private RadioButton mXiaoliang;
    /**
     * 价格
     */
    private RadioButton mJiage;
    private SublistPresenter presenter;
    private GridLayoutManager layoutManager1;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        ButterKnife.bind(this);
        Intent intent = getIntent();
        pscid = intent.getStringExtra("pscid");
        loadData();

        adapter = new SublistAdapter(this, list);
        layoutManager1 = new GridLayoutManager(Main2Activity.this,2);
        manager = new LinearLayoutManager(this);
        mRcvKind.setLayoutManager(manager);
        mRcvKind.setLoadingMoreEnabled(true);
        mRcvKind.setLoadingListener(this);
        mRcvKind.setAdapter(adapter);
        loadData();

    }

    private void loadData() {

        presenter = new SublistPresenter(this);
        presenter.getData(url, pscid, page, sort);

    }

    @OnClick({R.id.iv_kind_back, R.id.iv_kind_type, R.id.tv_shaixuan, R.id.btn_jingdong, R.id.btn_new_shop, R.id.btn_grade, R.id.btn_caizhi, R.id.rcv_kind})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_kind_back:
                finish();
                break;
            case R.id.zonghe:
                presenter.getData(url, pscid, page, 0);
                adapter.notifyDataSetChanged();
                break;
            case R.id.xiaoliang:
                presenter.getData(url, pscid, page, 1);
                adapter.notifyDataSetChanged();
                break;
            case R.id.jiage:
                presenter.getData(url, pscid, page, 2);
                adapter.notifyDataSetChanged();
                break;
            case R.id.iv_kind_type:
                if(flag){
                    mRcvKind.setLayoutManager(manager);
                    adapter.notifyDataSetChanged();
                    flag=false;
                }else {
                    mRcvKind.setLayoutManager(layoutManager1);
                    adapter.notifyDataSetChanged();
                    flag=true;
                }
                break;

        }
    }

    @Override
    public void onRefresh() {
        isFresh = true;
        page = 1;
        loadData();
    }

    @Override
    public void onLoadMore() {
        isFresh = false;
        page += 1;

        loadData();
    }

    @Override
    public void onSuccess(List<SublistBean.DataBean> newslist) {
        if (isFresh) {
            mRcvKind.refreshComplete();

        } else {
            mRcvKind.loadMoreComplete();

        }
        if (newslist != null) {
            if (isFresh) {
                list.clear();
            }
            list.addAll(newslist);
            adapter.notifyDataSetChanged();
        }
        if (page == 3) {
            Toast.makeText(this, "没有更多数据啦!", Toast.LENGTH_LONG).show();
            mRcvKind.loadMoreComplete();

        }

    }

    @Override
    public void onFailed(Exception e) {

    }

    private void initView() {
        mZonghe = (RadioButton) findViewById(R.id.zonghe);
        mZonghe.setOnClickListener(this);
        mXiaoliang = (RadioButton) findViewById(R.id.xiaoliang);
        mXiaoliang.setOnClickListener(this);
        mJiage = (RadioButton) findViewById(R.id.jiage);
        mJiage.setOnClickListener(this);
    }



}
