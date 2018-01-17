package com.example.wan.myjingdong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wan.myjingdong.adapter.ViewPagerAdapter;
import com.example.wan.myjingdong.bean.DetailBean;
import com.example.wan.myjingdong.callback.AddView;
import com.example.wan.myjingdong.callback.DetailView;
import com.example.wan.myjingdong.presaenter.AddPresenter;
import com.example.wan.myjingdong.presaenter.DetailPresenter;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailView, AddView, View.OnClickListener {


    private String uid;
    private String pid;
    private ImageView mBack;
    private TabLayout mTabLayout;
    private ImageView mFenxiang;
    private View mView;
    private ViewPager mPager;
    private ImageView mCart;
    private ImageView mAddcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        ButterKnife.bind(this);
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        SharedPreferences sharedPreferences = getSharedPreferences("pid",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("pid",pid).commit();
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        uid = preferences.getString("id", "s");
        DetailPresenter presenter = new DetailPresenter();
        presenter.attachView(this);
        presenter.getNews(pid);

    }


    @Override
    public void failed(Exception e) {

    }

    @Override
    public void success(DetailBean.DataBean data) {


    }

    @Override
    public void successAdd(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void failedAdd(Exception e) {

    }


    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mFenxiang = (ImageView) findViewById(R.id.fenxiang);
        mView = (View) findViewById(R.id.view);
        mPager = (ViewPager) findViewById(R.id.pager);
        mCart = (ImageView) findViewById(R.id.cart);
        mAddcart = (ImageView) findViewById(R.id.addcart);
        mBack.setOnClickListener(this);
        mFenxiang.setOnClickListener(this);
        mCart.setOnClickListener(this);
        mAddcart.setOnClickListener(this);
        mTabLayout.addTab(mTabLayout.newTab().setText("商品"));
        mTabLayout.addTab(mTabLayout.newTab().setText("详情"));
        mTabLayout.addTab(mTabLayout.newTab().setText("评价"));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mPager.setAdapter(viewPagerAdapter);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.fenxiang:
                break;
            case R.id.cart:
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("id",3);
                startActivity(intent);
                break;
            case R.id.addcart:
                SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean("isEmpty", false);
                edit.commit();
                AddPresenter addPresenter = new AddPresenter();
                addPresenter.attach(this);
                addPresenter.getData(pid, uid);
                break;
        }
    }

}