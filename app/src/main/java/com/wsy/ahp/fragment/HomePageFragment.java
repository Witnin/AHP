package com.wsy.ahp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wsy.ahp.R;
import com.wsy.ahp.activity.GreetingActivity;
import com.wsy.ahp.activity.HiRefreshDemoActivity;
import com.wsy.ahp.activity.HiTabTopDemoActivity;
import com.wsy.ahp.activity.HomeActivity;
import com.wsy.ahp.activity.OnClickActivity;
import com.wsy.ahp.activity.banner.HiBannerDemoActivity;
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
        Button refreshButton = getActivity().findViewById(R.id.hiRefresh);
        Button bannerButton = getActivity().findViewById(R.id.hiBanner);
        Button jetpackComposeButton = getActivity().findViewById(R.id.jetpackCompose);
        Button kotlinExtensionButton = getActivity().findViewById(R.id.kotlinExtension);
        Button homeBottom = getActivity().findViewById(R.id.homeBottom);
        botton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HiTabTopDemoActivity.class);
            startActivity(intent);
        });

        refreshButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HiRefreshDemoActivity.class);
            startActivity(intent);
        });

        bannerButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HiBannerDemoActivity.class);
            startActivity(intent);
        });
        jetpackComposeButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), GreetingActivity.class);
            startActivity(intent);
        });
        kotlinExtensionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), OnClickActivity.class);
            startActivity(intent);
        });
        homeBottom.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        });


    }
}
