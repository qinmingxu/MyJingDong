package com.example.wan.myjingdong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView mText01;
    /**
     * 立即下单
     */
    private TextView mText02;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        price = intent.getStringExtra("price");
        initView();
        //Toast.makeText(CreateOrderActivity.this,price,Toast.LENGTH_SHORT).show();
        mText02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CreateOrderActivity.this, OrderActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }

    private void initView() {
        mText01 = (TextView) findViewById(R.id.text01);
        mText02 = (TextView) findViewById(R.id.text02);
        mText01.setText("实付款¥"+price);
    }
}
