package com.wsy.ahp.activity.scroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.wsy.ahp.activity.scroll.util.FlingHelper;

import java.util.Objects;

public class WsyNestedScrollLayout extends NestedScrollView {


    public WsyNestedScrollLayout(@NonNull Context context) {
        this(context, null);
    }

    public WsyNestedScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WsyNestedScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }


    /**
     * 嵌套滑动的两个角色：NestedScrollingParent3和NestedScrollingChild3，是由NestedScrollingChild3触发嵌套滑动，由NestedScrollingParent3触发不算嵌套滑动
     * 小结：子控件触发dispatchNestedPreScroll时会先调用支持嵌套滚动父控件的onNestedPreScroll让父控件先滚动，再执行
     * 自身的dispatchNestedScroll进行滚动
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        int mHeadView = ((LinearLayout) getChildAt(0)).getChildAt(0).getMeasuredHeight();
        int mHeadViewImage = ((LinearLayout) getChildAt(0)).getChildAt(1).getMeasuredHeight();
        //向上滚动并且滚动的距离小于头部控件的高度，则此时父控件先滚动并记录消费的滚动距离
        boolean hideTop = dy > 0 && getScrollY() < (mHeadView + mHeadViewImage);

        if (hideTop) {
            //滚动到相应的滑动距离
            scrollBy(0, dy);
            //记录父控件消费的滚动记录，防止子控件重复滚动
            consumed[1] = dy;
        }
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY);
        //3.1记录惯性滚动的速度
        if (velocityY > 0) {

        } else {
            ViewPager2 viewPager2 = getChildView(this, ViewPager2.class);
            if (viewPager2 != null) {
                RecyclerView childRecyclerView = getChildView(((ViewGroup) viewPager2.getChildAt(0)), RecyclerView.class);
                if (childRecyclerView != null) {
                    childRecyclerView.fling(0, velocityY);
                }
            }
        }
    }


    private <T> T getChildView(View viewGroup, Class<T> targetClass) {
        if (viewGroup != null && viewGroup.getClass() == targetClass) {
            return (T) viewGroup;
        }
        if (viewGroup instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) viewGroup).getChildCount(); i++) {
                View view = ((ViewGroup) viewGroup).getChildAt(i);
                if (view instanceof ViewGroup) {
                    T result = getChildView(view, targetClass);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }
}