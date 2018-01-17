package com.example.wan.myjingdong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wan.myjingdong.adapter.MyOrderAdapter;
import com.example.wan.myjingdong.bean.Orderbean;
import com.example.wan.myjingdong.callback.IOrderActivityListener;
import com.example.wan.myjingdong.presaenter.OrderPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener,IOrderActivityListener {

    private ImageView mImage;
    /**
     * 待支付
     */
    private Button mButton1;
    /**
     * 已支付
     */
    private Button mButton2;
    /**
     * 已取消
     */
    private Button mButton3;
    private XRecyclerView mRecycle;
    private OrderPresenter orderPresenter;
    private MyOrderAdapter myOrderAdapter;
    private List<Orderbean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        orderPresenter = new OrderPresenter(this);
        orderPresenter.getOrder(0+"");
    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.image);
        mImage.setOnClickListener(this);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mRecycle = (XRecyclerView) findViewById(R.id.recycle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.image:
                break;
            case R.id.button1:
                orderPresenter.getOrder(0+"");
                break;
            case R.id.button2:
                orderPresenter.getOrder(1+"");
                break;
            case R.id.button3:
                orderPresenter.getOrder(2+"");
                break;
        }
    }

    @Override
    public void show(Orderbean orderbean) {
        data = orderbean.getData();
        LinearLayoutManager layoutManager=new LinearLayoutManager(OrderActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycle.setLayoutManager(layoutManager);
        myOrderAdapter = new MyOrderAdapter(OrderActivity.this,data);
        mRecycle.setAdapter(myOrderAdapter);
    }
}
