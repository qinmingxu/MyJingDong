package com.example.wan.myjingdong;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.wan.myjingdong.R;
import com.example.wan.myjingdong.fragment.CartFragment;
import com.example.wan.myjingdong.fragment.CateFragment;
import com.example.wan.myjingdong.fragment.FindFragment;
import com.example.wan.myjingdong.fragment.HomeFragment;
import com.example.wan.myjingdong.fragment.UserFragment;
import com.example.wan.myjingdong.http.BottomTabBarExt;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar mb;
    private int num=5;
    private ImageView img;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            num--;
            if(num==0){
                mb.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
            }
            handler.sendEmptyMessageDelayed(1,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mb=(BottomTabBar)findViewById(R.id.bottom_tab_bar);
        img = (ImageView) findViewById(R.id.img);
        mb.init(getSupportFragmentManager())
                .setImgSize(60,60)
                .setFontSize(10)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("主页",R.mipmap.home, HomeFragment.class)
                .addTabItem("分类",R.mipmap.liebiao, CateFragment.class)
                .addTabItem("发现",R.mipmap.find, FindFragment.class)
                .addTabItem("购物车",R.mipmap.shoppingcart, CartFragment.class)
                .addTabItem("我的",R.mipmap.mine, UserFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
        handler.sendEmptyMessage(1);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        if(id==3){
            mb.setVisibility(View.VISIBLE);
            img.setVisibility(View.GONE);
            BottomTabBarExt bottomTabBarExt = new BottomTabBarExt();
            try {
                bottomTabBarExt.setCurrentTab(mb,3);
            } catch (ClassNotFoundException e) {
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
