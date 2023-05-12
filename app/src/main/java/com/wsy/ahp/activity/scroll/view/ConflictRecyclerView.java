package com.wsy.ahp.activity.scroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.wsy.ahp.R;
import com.wsy.ahp.activity.scroll.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 不可滚动的RecyclerView
 */
public class ConflictRecyclerView extends RecyclerView {

    public ConflictRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public ConflictRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ConflictRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayoutManager(new GridLayoutManager(getContext(),3, GridLayoutManager.VERTICAL, false));
        RecyclerAdapter bannerAdapter = new RecyclerAdapter(getContext(),getBanner(),getImage());
        setAdapter(bannerAdapter);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }

    private List<String> getBanner() {
        List<String> data = new ArrayList<>();
        data.add("ParentView item 0");
        data.add("ParentView item 1");
        data.add("ParentView item 2");
        data.add("ParentView item 3");
        data.add("ParentView item 4");
        data.add("ParentView item 5");
        data.add("ParentView item 6");
        data.add("ParentView item 7");
        data.add("ParentView item 8");
        return data;
    }

    private List<Integer> getImage() {
        List<Integer> data = new ArrayList<>();
        data.add(R.drawable.guide_bg1);
        data.add(R.drawable.guide_bg2);
        data.add(R.drawable.guide_bg3);
        data.add(R.drawable.guide_bg4);
        data.add(R.drawable.guide_bg1);
        data.add(R.drawable.guide_bg2);
        data.add(R.drawable.guide_bg3);
        data.add(R.drawable.guide_bg4);
        data.add(R.drawable.guide_bg1);

        return data;
    }
}
