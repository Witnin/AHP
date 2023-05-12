package com.wsy.ahp.activity.scroll;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wsy.ahp.R;
import com.wsy.ahp.activity.scroll.adapter.RecyclerFragmentAdapter;
import com.wsy.ahp.activity.scroll.fragment.RecyclerViewFragment;
import com.wsy.ahp.http.common.ArouterUrl;

import java.util.ArrayList;
import java.util.List;

@Route(path = ArouterUrl.NESTED_SCROLL)
public class ScrollActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager2 viewPager = findViewById(R.id.viewpager_view);
        viewPager.setAdapter(new RecyclerFragmentAdapter(this,getPageFragments()));
        final String[] labels = new String[]{"水质", "土壤", "其他"};
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(labels[position]);
            }
        }).attach();

    }

    private List<Fragment> getPageFragments(){
        List<Fragment> data = new ArrayList<>();
        data.add(new RecyclerViewFragment());
        data.add(new RecyclerViewFragment());
        data.add(new RecyclerViewFragment());
        return data;
    }
}
