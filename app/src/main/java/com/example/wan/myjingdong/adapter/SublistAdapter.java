package com.example.wan.myjingdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wan.myjingdong.DetailActivity;
import com.example.wan.myjingdong.R;
import com.example.wan.myjingdong.bean.SublistBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by SDC on 2017/12/5.
 */

public class SublistAdapter extends XRecyclerView.Adapter<SublistAdapter.ViewHolder> {
    private Context con;
    private List<SublistBean.DataBean> list;

    public SublistAdapter(Context con, List<SublistBean.DataBean> list) {
        this.con = con;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(con, R.layout.sublist_item, null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.price.setText("￥"+list.get(position).getPrice());
//        list.get(position).get
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(con).load(split[0]).into(holder.img);
        int pid = list.get(position).getPid();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(con, DetailActivity.class);
                intent.putExtra("pid",list.get(position).getPid()+"");
                con.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView price;

        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.sublist_title);
            price = itemView.findViewById(R.id.sublist_price);

            img = itemView.findViewById(R.id.sublist_img);

        }
    }
}
