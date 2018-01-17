package com.example.wan.myjingdong.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by SDC on 2017/11/14.
 */

public class HomeBottomView extends RecyclerView {
    public HomeBottomView(Context context) {
        super(context);
    }

    public HomeBottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeBottomView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

    }
}
