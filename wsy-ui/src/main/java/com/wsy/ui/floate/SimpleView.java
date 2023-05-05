package com.wsy.ui.floate;

import android.content.Context;
import android.view.LayoutInflater;

import com.dalimao.library.DragView;
import com.wsy.ui.R;

public class SimpleView extends DragView {
    public SimpleView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.float_sample, this);
    }
}
