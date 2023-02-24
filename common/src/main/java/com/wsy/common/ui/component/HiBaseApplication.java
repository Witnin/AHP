package com.wsy.common.ui.component;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.wsy.common.ActivityManager;
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
            public JsonParser injectJsonParser() {
                return (src) -> new Gson().toJson(src);
            }

            @Override
            public boolean includeThread() {
                return true;
            }
        }, new HiConsolePrinter(), HiFilePrinter.getInstance(getCacheDir().getAbsolutePath(), 0));
    }
}
