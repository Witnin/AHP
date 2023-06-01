package com.wsy.ahp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wsy.ahp.R;
import com.wsy.ahp.activity.GreetingActivity;
import com.wsy.ahp.activity.HiRefreshDemoActivity;
import com.wsy.ahp.activity.HiTabTopDemoActivity;
import com.wsy.ahp.activity.HomeActivity;
import com.wsy.ahp.activity.OnClickActivity;
import com.wsy.ahp.activity.TestActivity;
import com.wsy.ahp.activity.animation.AnimationActivity;
import com.wsy.ahp.activity.threed.GlGlobeActivity;
import com.wsy.ahp.activity.threed.GlLineActivity;
import com.wsy.ahp.activity.threed.PanoramaActivity;
import com.wsy.ahp.activity.banner.HiBannerDemoActivity;
import com.wsy.ahp.http.common.ArouterUrl;
import com.wsy.common.ui.component.HiBaseFragment;
import com.wsy.wsy_library.executor.HiExecutor;

public class OtherFragment extends HiBaseFragment {

    Boolean paused =false;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_page;
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
        TextView myTextView = getActivity().findViewById(R.id.myTextView);
        Button botton = getActivity().findViewById(R.id.mybutton);
        Button refreshButton = getActivity().findViewById(R.id.hiRefresh);
        Button bannerButton = getActivity().findViewById(R.id.hiBanner);
        Button jetpackComposeButton = getActivity().findViewById(R.id.jetpackCompose);
        Button kotlinExtensionButton = getActivity().findViewById(R.id.kotlinExtension);
        Button homeBottom = getActivity().findViewById(R.id.homeBottom);
        Button threedBottom = getActivity().findViewById(R.id.threedBottom);
        Button btn_gl_globe = getActivity().findViewById(R.id.btn_gl_globe);
        Button btn_gl_line = getActivity().findViewById(R.id.btn_gl_line);
        Button anim_bottom = getActivity().findViewById(R.id.anim_bottom);
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
        threedBottom.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PanoramaActivity.class);
            startActivity(intent);
        });
        btn_gl_globe.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), GlGlobeActivity.class);
            startActivity(intent);
        });
        btn_gl_line.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), GlLineActivity.class);
            startActivity(intent);
        });

        myTextView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TestActivity.class);
            startActivity(intent);
        });
        anim_bottom.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AnimationActivity.class);
            startActivity(intent);
        });

        layoutView.findViewById(R.id.profile).setOnClickListener(v ->navigation("/home/detail"));
        layoutView.findViewById(R.id.vip).setOnClickListener(v ->navigation("/vip/detail"));
        layoutView.findViewById(R.id.auth).setOnClickListener(v ->navigation("/auth/detail"));
        layoutView.findViewById(R.id.unknow).setOnClickListener(v ->{navigation("/profile/unknow");});
        layoutView.findViewById(R.id.greeting).setOnClickListener(v ->{navigation("/home/greeting");});
        layoutView.findViewById(R.id.login).setOnClickListener(v ->{navigation("/home/Login");});
        layoutView.findViewById(R.id.relative_parent).setOnClickListener(v ->{navigation("/relative/parent");});
        layoutView.findViewById(R.id.relative_brother).setOnClickListener(v ->{navigation("/relative/brother");});
        layoutView.findViewById(R.id.native_login).setOnClickListener(v ->{navigation("/native/login");});
        layoutView.findViewById(R.id.map_base).setOnClickListener(v ->{navigation(ArouterUrl.HOME_MAP);});
        layoutView.findViewById(R.id.map_weather).setOnClickListener(v ->{navigation(ArouterUrl.MAP_WEATHER);});
        layoutView.findViewById(R.id.nested_scroll).setOnClickListener(v ->{navigation(ArouterUrl.NESTED_SCROLL);});
        layoutView.findViewById(R.id.nested).setOnClickListener(v ->{navigation(ArouterUrl.NESTED);});
        layoutView.findViewById(R.id.listview_article).setOnClickListener(v ->{navigation(ArouterUrl.LISTVIEW_ARTICLE);});
        layoutView.findViewById(R.id.recycle_view_article).setOnClickListener(v ->{navigation(ArouterUrl.RECYCLE_VIEW_ARTICLE);});
        layoutView.findViewById(R.id.recycle_view_water_flow).setOnClickListener(v ->{navigation(ArouterUrl.RECYCLE_VIEW_WATER_FLOW);});
        layoutView.findViewById(R.id.recycle_view_message).setOnClickListener(v ->{navigation(ArouterUrl.RECYCLE_VIEW_MESSAGE);});
        layoutView.findViewById(R.id.fragment_replace).setOnClickListener(v ->{navigation(ArouterUrl.FRAGMENT_REPLACE);});
        layoutView.findViewById(R.id.recycle_view_news).setOnClickListener(v ->{navigation(ArouterUrl.RECYCLE_VIEW_NEWS);});
        layoutView.findViewById(R.id.system_broadcast).setOnClickListener(v ->{navigation(ArouterUrl.SYSTEM_BROADCAST);});
        layoutView.findViewById(R.id.storage_file).setOnClickListener(v ->{navigation(ArouterUrl.STORAGE_FILE);});
        layoutView.findViewById(R.id.storage_sp).setOnClickListener(v ->{navigation(ArouterUrl.STORAGE_SP);});
        layoutView.findViewById(R.id.storage_db).setOnClickListener(v ->{navigation(ArouterUrl.STORAGE_DATABASE);});
        layoutView.findViewById(R.id.system_permission).setOnClickListener(v ->{navigation(ArouterUrl.SYSTEM_PERMISSION);});
        layoutView.findViewById(R.id.system_contact).setOnClickListener(v ->{navigation(ArouterUrl.SYSTEM_CONTACT);});
        layoutView.findViewById(R.id.system_notification).setOnClickListener(v ->{navigation(ArouterUrl.SYSTEM_NOTIFICATION);});

        layoutView.findViewById(R.id.executor1).setOnClickListener(v ->{
            for (int priority = 0; priority < 10;priority++){
                int finalPriority = priority;
                HiExecutor.INSTANCE.execute(priority,() -> {
                    try {
                        Thread.sleep( 1000 - finalPriority * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            });

        }});
        layoutView.findViewById(R.id.executor2).setOnClickListener(v ->{
            if(paused){
                HiExecutor.INSTANCE.resume();
            }else{
                HiExecutor.INSTANCE.pause();
            }
            paused = !paused;
        });
        layoutView.findViewById(R.id.executor3).setOnClickListener(v ->{
            HiExecutor.INSTANCE.execute(new HiExecutor.Callable<String>() {

                @Override
                public void onCompleted(String s) {
                    Log.e("executor", "onCompleted: -当前线程时"+Thread.currentThread().getName() );
                    Log.e("executor", "onCompleted: -任务结果result"+s );
                }

                @Override
                public String onBackground() {
                    Log.e("executor", "onBackground: -当前线程时"+Thread.currentThread().getName() );
                    return "我是异步任务的结果";
                }
            });
        });






    }

    private void navigation(String s) {
        ARouter.getInstance().build(s).navigation();
    }
}
