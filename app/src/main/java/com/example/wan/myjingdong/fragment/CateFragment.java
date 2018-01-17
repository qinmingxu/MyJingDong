package com.example.wan.myjingdong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wan.myjingdong.R;
import com.example.wan.myjingdong.adapter.LeftAdapter;
import com.example.wan.myjingdong.adapter.RightAdapter;
import com.example.wan.myjingdong.bean.DataBean;
import com.example.wan.myjingdong.bean.MessagesBean;
import com.example.wan.myjingdong.callback.MyView;
import com.example.wan.myjingdong.callback.RightView;
import com.example.wan.myjingdong.presaenter.MyPresenter;
import com.example.wan.myjingdong.presaenter.RightPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDC on 2017/11/3.
 */

public class CateFragment extends Fragment implements MyView, RightView {

    private ListView mLvLeft;
    private ListView mLvRight;
    private List<DataBean> data = new ArrayList<>();
    private LeftAdapter leftAadapter;
    private List<MessagesBean.DataBean> datas = new ArrayList<>();
    private RightAdapter rightAdapter;
    private RightPresenter presenter1;


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cate_fragment, container, false);
        Fresco.initialize(getContext());

        initView();
        MyPresenter presenter = new MyPresenter();
        presenter.attachView(this);
        presenter.getData();
        leftAadapter = new LeftAdapter(getContext(),data);
        rightAdapter = new RightAdapter(getContext(), datas);
        presenter1 = new RightPresenter();
        presenter1.attachView(this);
        presenter1.getData(1+"");
        mLvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                presenter1.getData(i+1+"");
//                Drawable drawable = getResources().getDrawable(R.drawable.mylist_view);
////                mLvLeft.setSelection(i);
//                mLvLeft.setSelector(drawable);
//                Toast.makeText(MainActivity.this,"这是第"+i+"条",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void initView() {
        mLvLeft = (ListView) view.findViewById(R.id.lv_left);
//        mLvLeft.setOnClickListener(this);
        mLvRight = (ListView) view.findViewById(R.id.lv_right);
//        mLvRight.setOnClickListener(this);
    }



    @Override
    public void success(List<DataBean> news) {

        if(news!=null){
            data.addAll(news);
            mLvLeft.setAdapter(leftAadapter);
        }

    }


    @Override
    public void show(List<MessagesBean.DataBean> news) {
        datas.clear();
        if(news!=null){
            datas.addAll(news);
            mLvRight.setAdapter(rightAdapter);
            rightAdapter.notifyDataSetChanged();
        }
    }



    @Override
    public void failed(Exception e) {

    }


}
