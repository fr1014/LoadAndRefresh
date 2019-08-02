package com.fr.loadandrefresh.application;

import android.app.Application;
import android.content.Context;

import com.fr.loadandrefresh.network.RetrofitManager;

public class BaseApplication extends Application {

    private static Context mContext;

    public BaseApplication() {
        mContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance().init();
    }

    public static Context getContext() {
        return mContext;
    }
}
