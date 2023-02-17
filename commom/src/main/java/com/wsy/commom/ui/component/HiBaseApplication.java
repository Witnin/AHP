package com.wsy.commom.ui.component;

import android.app.Application;

import com.google.gson.Gson;
import com.wsy.wsy_library.log.HiConsolePrinter;
import com.wsy.wsy_library.log.HiFilePrinter;
import com.wsy.wsy_library.log.HiLogConfig;
import com.wsy.wsy_library.log.HiLogManager;




public class HiBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
    }

    private void initLog() {
        HiLogManager.init(new HiLogConfig() {
            @Override
            public HiLogConfig.JsonParser injectJsonParser() {
                return (src) -> new Gson().toJson(src);
            }

            @Override
            public boolean includeThread() {
                return true;
            }
        }, new HiConsolePrinter(), HiFilePrinter.getInstance(getCacheDir().getAbsolutePath(), 0));
    }
}
