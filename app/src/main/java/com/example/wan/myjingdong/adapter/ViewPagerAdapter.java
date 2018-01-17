package com.example.wan.myjingdong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wan.myjingdong.fragment.DetailFragment01;
import com.example.wan.myjingdong.fragment.DetailFragment02;
import com.example.wan.myjingdong.fragment.DetailFragment03;


/**
 * Created by wan on 2018/1/9.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;
    public ViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                DetailFragment01 tab1=new DetailFragment01();
                return tab1;
            case 1:
                DetailFragment02 tab2=new DetailFragment02();
                return tab2;
            case 2:
                DetailFragment03 tab3=new DetailFragment03();
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
