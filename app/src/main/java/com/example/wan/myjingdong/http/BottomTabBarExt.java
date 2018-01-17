package com.example.wan.myjingdong.http;

import com.hjm.bottomtabbar.BottomTabBar;
import com.hjm.bottomtabbar.custom.CustomFragmentTabHost;

import java.lang.reflect.Field;

/**
 * Created by wan on 2018/1/12.
 */

public class BottomTabBarExt {
    public static void setCurrentTab(BottomTabBar bar, int current) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Class bottomTabClass = Class.forName(BottomTabBar.class.getName());
        Field TabHost = bottomTabClass.getDeclaredField("mTabHost");
        TabHost.setAccessible(true);
        CustomFragmentTabHost TabValue = (CustomFragmentTabHost) TabHost.get(bar);
        TabValue.setCurrentTab(current);
    }
}
