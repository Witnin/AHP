package com.wsy.ahp.activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wsy.ahp.R;
import com.wsy.ahp.adapter.LaunchImproveAdapter;
import com.wsy.ahp.http.common.ArouterUrl;
import com.wsy.common.ui.component.HiBaseActivity;
import com.wsy.wsy_library.util.HiStatusBar;


public class LaunchImproveActivity extends AppCompatActivity {
    // 声明引导页面的图片数组
    private int[] lanuchImageArray = {R.drawable.guide_bg1,
            R.drawable.guide_bg2, R.drawable.guide_bg3, R.drawable.guide_bg4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        HiStatusBar.setStatusBar(this,true, Color.WHITE,false);
        setContentView(R.layout.activity_launch_improve);
        ViewPager vp_launch = findViewById(R.id.vp_launch);
        LaunchImproveAdapter adapter = new LaunchImproveAdapter(getSupportFragmentManager(), lanuchImageArray);
        vp_launch.setAdapter(adapter);
    }
}
