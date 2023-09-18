package com.wsy.ahp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wingsofts.threedlayout.ThreeDLayout;
import com.wsy.ahp.R;
import com.wsy.ahp.http.common.ArouterUrl;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.Screen;

@Route(path = ArouterUrl.threeDimen)
public class ThreeDimenActivity extends AppCompatActivity {

    ThreeDLayout tdAvatar;
    ThreeDLayout tdRoot;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_dimen);

        tdAvatar = (ThreeDLayout) findViewById(R.id.td_avatar);
        tdAvatar.setTouchMode(ThreeDLayout.MODE_BOTH_X_Y);
        tdAvatar.setMaxRotateDegree(30);

        tdRoot = (ThreeDLayout) findViewById(R.id.td_root);

        dialog =new  AlertDialog.Builder(this).setMessage("refreshing....").create();





    }


    public void refresh(View view) {
        dialog.show();
        tdRoot.startHorizontalAnimate();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                dialog.dismiss();
                tdRoot.stopAnimate();
            });
        }).start();


    }
}