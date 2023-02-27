package com.wsy.ahp.activity.animation;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.wsy.ahp.R;
import com.wsy.ahp.activity.animation.util.CoverFlow;

public class AnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏导航栏
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }

        CoverFlow cf = new CoverFlow(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏

        cf.setBackgroundDrawable(this.getResources().getDrawable(R.mipmap.background));//背景

        cf.setAdapter(new ImageAdapter(this));

        ImageAdapter imageAdapter = new ImageAdapter(this);

        cf.setAdapter(imageAdapter);

        cf.setSelection(2, true);

        cf.setAnimationDuration(1000);


        setContentView(cf);



        TextView textView = new TextView(this);
        // 第一个参数为宽的设置，第二个参数为高的设置
        textView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT));
// 设置textView的文字
        textView.setText("勘验终端");
// 设置字体大小
        textView.setTextSize(40);
// 设置背景

// 设置字体颜色
        textView.setTextColor(Color.RED);
//设置居中
        textView.setGravity(Gravity.CENTER);
//设置边距
        textView.setPadding(10, 20, 10, 0);//left, top, right, bottom
    }
}
