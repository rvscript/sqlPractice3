package com.example.rv193.sqlproject3.application;

import android.app.Application;

import com.example.rv193.sqlproject3.BuildConfig;

import timber.log.Timber;

public class sqlApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new NoDebugTree());
        }
    }
}
