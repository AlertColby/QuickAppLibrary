package com.lct.quickapplibrary.app;

import android.app.Application;
import android.content.Context;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\7\2 0002 12:04
 * 邮箱:chen510470614@163.com
 */
public class MainApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        if( context == null){
            context = this;
        }
    }
}
