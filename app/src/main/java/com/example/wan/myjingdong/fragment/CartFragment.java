package com.example.wan.myjingdong.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wan.myjingdong.R;
import com.example.wan.myjingdong.adapter.CartsAdapter;
import com.example.wan.myjingdong.bean.MessageBean;
import com.example.wan.myjingdong.callback.CartsView;
import com.example.wan.myjingdong.callback.DeleteView;
import com.example.wan.myjingdong.entity.MessageEvent;
import com.example.wan.myjingdong.entity.PriceAndNum;
import com.example.wan.myjingdong.entity.SomeId;
import com.example.wan.myjingdong.presaenter.CartsPresenter;
import com.example.wan.myjingdong.presaenter.DeletePresenter;
import com.example.wan.myjingdong.CreateOrderActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by SDC on 2017/11/3.
 */

public class CartFragment extends Fragment implements CartsView, View.OnClickListener, DeleteView {
    private static final String TAG = "MainActivity";
    private ExpandableListView mElv;
    /**
     * 全选
     */
    private CheckBox mAllChekbox;
    /**
     * ￥0.00
     */
    private TextView mTvTotalPrice;
    /**
     * 结算(0)
     */
    private TextView mTvGoToPay;
    private List<MessageBean.DataBean> groupList= new ArrayList<>();
    private  List<List<MessageBean.DataBean.ListBean>> childList= new ArrayList<>();
    private CartsAdapter adapter;

    private TextView tvEdit;
    private LinearLayout llShar;
    private boolean flag=true;
    private LinearLayout llInfo;
    private TextView tvCommit;
    private TextView tvDelete;
    private View view;
    private LinearLayout cart_empty;
    private String uid;
    private CartsPresenter presenter;
    private SharedPreferences preferences;
    private int size;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cart_fragment, container, false);
        initView();
        EventBus.getDefault().register(this);
        preferences = getContext().getSharedPreferences("data", MODE_PRIVATE);

        Log.e("zxz", "onCreate: "+uid );

//        presenter.getData(uid);


        adapter = new CartsAdapter(getContext(),cart_empty,groupList,childList);
        PriceAndNum priceAndNum = new PriceAndNum();

        mElv.setAdapter(adapter);
        mElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                return true;
            }
        });
        adapter.notifyDataSetChanged();
        return view;
    }

    private void goneAndVisibility() {
        if(flag==false){
            llShar.setVisibility(View.VISIBLE);
            tvCommit.setVisibility(View.VISIBLE);
            tvEdit.setVisibility(View.GONE);
            llInfo.setVisibility(View.GONE);
        }else{
            llShar.setVisibility(View.GONE);
            tvCommit.setVisibility(View.GONE);
            tvEdit.setVisibility(View.VISIBLE);
            llInfo.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        cart_empty = view.findViewById(R.id.layout_cart_empty);
        mElv = (ExpandableListView) view.findViewById(R.id.exListView);
        mAllChekbox = (CheckBox) view.findViewById(R.id.all_chekbox);
        mTvTotalPrice = (TextView) view.findViewById(R.id.tv_total_price);
        mTvGoToPay = (TextView) view.findViewById(R.id.tv_go_to_pay);
        tvEdit = (TextView)view.findViewById(R.id.tv_edit);
        llShar = (LinearLayout) view.findViewById(R.id.ll_shar);
        llInfo = (LinearLayout) view.findViewById(R.id.ll_info);
        tvCommit = (TextView) view.findViewById(R.id.tv_commit);
        tvDelete = (TextView) view.findViewById(R.id.tv_delete);
        tvDelete.setOnClickListener(this);
        tvCommit.setOnClickListener(this);
        mAllChekbox.setOnClickListener(this);
        tvEdit.setOnClickListener(this);
        mTvGoToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mTvTotalPrice.getText().toString();
                if(s.equals("￥0.00")){
                    Toast.makeText(getContext(),"请选择商品",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), CreateOrderActivity.class);
                    intent.putExtra("price",s);
                    startActivity(intent);
                }


            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_chekbox:
                adapter.changeAllListCbState(mAllChekbox.isChecked());
                break;
            case R.id.tv_edit:
                flag=false;
                goneAndVisibility();
                break;
            case R.id.tv_commit:
                flag=true;
                goneAndVisibility();
                break;
            case R.id.tv_delete:
//                adapter.delAll(true);

                break;
        }
    }

    @Override
    public void success(List<MessageBean.DataBean> data) {
        List<MessageBean.DataBean> data1 = data;
        data1.remove(0);
        if(data!=null){

            groupList.addAll(data1);

            for (int i = 0; i < data.size(); i++) {
                List<MessageBean.DataBean.ListBean> datas = data.get(i).getList();
                childList.add(datas);
            }
            mElv.setGroupIndicator(null);
            for(int i = 0; i < groupList.size(); i++){
                mElv.expandGroup(i);
            }
        }

        adapter.notifyDataSetChanged();


    }
    @Override
    public void onStart() {
        super.onStart();
    }




    @Override
    public void failed(Exception e) {
        Log.e(TAG, "failed: "+e );
    }
    @Subscribe
    public void onMessageEvent(PriceAndNum event) {

        mTvGoToPay.setText("结算(" + event.getNum() + ")");
        mTvTotalPrice.setText("￥"+event.getPrice() );


    }
    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mAllChekbox.setChecked(event.isChecked());
    }
    @Subscribe
    public void onMessageEvent(SomeId event) {

        size = event.getSize();

        int pid = event.getPid();
        if(size==0){
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("isEmpty",true);
            edit.commit();
        }
        Log.e("zxz",pid+"我要删除了"+ size);
        DeletePresenter deletePresenter = new DeletePresenter();
        deletePresenter.attach(this);
        deletePresenter.getDelete(uid,pid+"");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(groupList.size()!=0||childList.size()!=0){
            groupList.clear();
            childList.clear();
        }

        boolean isEmpty = preferences.getBoolean("isEmpty", true);
        if(isEmpty==false){
            cart_empty.setVisibility(View.GONE);

        }
        boolean isLogin = preferences.getBoolean("isLogin", false);
        if(isLogin==true){
            presenter = new CartsPresenter();
            presenter.attach(this);
            uid = preferences.getString("id", "s");

            presenter.getData(uid);
        }


    }

    @Override
    public void successDel(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failedDel() {

    }
}
