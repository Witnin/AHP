package com.wsy.ahp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wsy.ahp.R;
import com.wsy.ahp.activity.HiTabTopDemoActivity;
import com.wsy.common.ui.component.HiBaseFragment;

public class HomePageFragment extends HiBaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Button botton = getActivity().findViewById(R.id.mybutton);
//        botton.setOnClickListener(v -> {
//            Intent intent = new Intent(getActivity(), HiTabTopDemoActivity.class);
//            startActivity(intent);
//        });
//
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button botton = getActivity().findViewById(R.id.mybutton);
        botton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HiTabTopDemoActivity.class);
            startActivity(intent);
        });
    }
}
