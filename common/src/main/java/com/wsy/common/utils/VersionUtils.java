package com.wsy.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class VersionUtils {
    //VersionCode和VersionName的获取：
    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getPackageVersionName(Context context, String pkgName) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(pkgName, 0); //PackageManager.GET_CONFIGURATIONS
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static int getPackageVersionCode(Context context, String pkgName) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(pkgName, 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {

    }

}
