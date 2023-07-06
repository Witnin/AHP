package com.wsy.ahp.activity.scroll;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wsy.ahp.R;
import com.wsy.ahp.activity.scroll.adapter.RecyclerFragmentAdapter;
import com.wsy.ahp.activity.scroll.fragment.RecyclerViewFragment;
import com.wsy.ahp.http.common.ArouterUrl;
import com.wsy.common.utils.StatusBar;

import java.util.ArrayList;
import java.util.List;

@Route(path = ArouterUrl.NESTED)
public class NestedScrollActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBar statusBar = new StatusBar(NestedScrollActivity.this);
//        //设置颜色为半透明
//        statusBar.setColor(R.color.translucent);
//        //设置颜色为透明
//        statusBar.setColor(R.color.transparent);
        //隐藏状态栏
        statusBar.hide();

        setContentView(R.layout.activity_scroll_nested);
        TabLayout tabLayout = findViewById(R.id.video_tab_layout);
        LinearLayout tabViewPager = findViewById(R.id.tab_viewpager);
        NestedScrollView nestedScrollView = findViewById(R.id.nested_scroll_view);
        ViewPager2 viewPager = findViewById(R.id.viewpager_view);
        viewPager.setAdapter(new RecyclerFragmentAdapter(this,getPageFragments()));
        final String[] labels = new String[]{"水质", "土壤", "其他"};
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(labels[position]);
            }
        }).attach();
        /**
         * header不滚动，内部滚动
         */
        tabViewPager.post(() -> {
            tabViewPager.getLayoutParams().height = nestedScrollView.getMeasuredHeight();
            tabViewPager.requestLayout();
        });

    }

    private List<Fragment> getPageFragments(){
        List<Fragment> data = new ArrayList<>();
        data.add(new RecyclerViewFragment());
        data.add(new RecyclerViewFragment());
        data.add(new RecyclerViewFragment());
        return data;
    }
}
