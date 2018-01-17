package com.example.wan.myjingdong.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wan.myjingdong.R;
import com.example.wan.myjingdong.bean.DetailBean;
import com.example.wan.myjingdong.entity.GlideImageLoader;
import com.example.wan.myjingdong.presaenter.Detail2Presenter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wan on 2018/1/9.
 */

public class DetailFragment01 extends Fragment implements IDetailFragmentListener {
    private View view;
    private Banner mBanner;
    private TextView mTitle;
    private TextView mSubhead;
    private Detail2Presenter detail2Presenter;
    private String string;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail01, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        string = getActivity().getSharedPreferences("pid", Context.MODE_PRIVATE).getString("pid", "");
        detail2Presenter = new Detail2Presenter(this);
        detail2Presenter.getData(string);

    }


    private void initView(View view) {
        mBanner = (Banner) view.findViewById(R.id.banner);
        mTitle = (TextView) view.findViewById(R.id.title);
        mSubhead = (TextView) view.findViewById(R.id.subhead);
    }

    @Override
    public void show(DetailBean detailBean) {
        String images2 = detailBean.getData().getImages();
        String[] split =images2.split("\\|");
        List<String> images =  new ArrayList<>();
        for (int i = 0; i <split.length ; i++) {
            images.add(split[i]);
        }
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        mTitle.setText(detailBean.getData().getTitle());
        mSubhead.setText(detailBean.getData().getSubhead());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        detail2Presenter.deach();
    }
}
