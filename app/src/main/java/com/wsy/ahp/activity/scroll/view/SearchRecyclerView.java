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
import com.wsy.ahp.activity.scroll.adapter.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 不可滚动的RecyclerView
 */
public class SearchRecyclerView extends RecyclerView {

    public SearchRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public SearchRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        SearchAdapter bannerAdapter = new SearchAdapter(getContext());
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


}
