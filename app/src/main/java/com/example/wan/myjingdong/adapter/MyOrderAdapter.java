package com.example.wan.myjingdong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wan.myjingdong.R;
import com.example.wan.myjingdong.bean.Orderbean;

import java.util.List;

/**
 * Created by wan on 2018/1/13.
 */
public class MyOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<Orderbean.DataBean> list;
    public MyOrderAdapter(Context context, List<Orderbean.DataBean> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.title.setText(list.get(position).getTitle());
        holder1.time.setText(list.get(position).getCreatetime());
        int status = list.get(position).getStatus();
        if(status==0){
            holder1.status.setTextColor(Color.RED);
            holder1.status.setText("待支付");
        }else if(status==1){
            holder1.status.setText("已支付");
        }else {
            holder1.status.setText("已取消");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final TextView time;
        private final TextView status;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);
        }
    }
}
