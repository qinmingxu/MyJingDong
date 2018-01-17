package com.example.wan.myjingdong.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wan.myjingdong.R;
import com.example.wan.myjingdong.bean.MessagesBean;

import java.util.List;

/**
 * Created by SDC on 2017/12/1.
 */

public class RightAdapter extends BaseAdapter {
    private Context con;
    private List<MessagesBean.DataBean> data;

    public RightAdapter(Context con, List<MessagesBean.DataBean> data) {
        this.con = con;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        List<MessagesBean.DataBean.ListBean> list = data.get(i).getList();
        if(view==null){
            view = View.inflate(con, R.layout.right_item,null);
            holder = new ViewHolder();
            holder.tvRight = view.findViewById(R.id.tv_right);
            holder.rightRcy = view.findViewById(R.id.right_rcy);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvRight.setText(data.get(i).getName());
        RightItemAdapter adapter = new RightItemAdapter(con, list);
        GridLayoutManager manager = new GridLayoutManager(con,3);
        holder.rightRcy.setLayoutManager(manager);
        holder.rightRcy.setAdapter(adapter);
        return view;
    }
    class ViewHolder{
        TextView tvRight;
        RecyclerView rightRcy;
    }
}
