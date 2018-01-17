package com.example.wan.myjingdong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wan.myjingdong.R;
import com.example.wan.myjingdong.presaenter.DetailPresenter;
import com.youth.banner.Banner;

/**
 * Created by wan on 2018/1/9.
 */

public class DetailFragment02 extends Fragment {
    private View view;
    private Banner mBanner;
    private TextView mTitle;
    private TextView mSubhead;
    private DetailPresenter detailPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail02, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
