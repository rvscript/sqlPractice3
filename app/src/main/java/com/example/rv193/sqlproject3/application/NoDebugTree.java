package com.example.rv193.sqlproject3.application;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

class NoDebugTree extends Timber.Tree {
    @Override
    protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
        //Intentionally left empty To hide timber upon release
        if (priority == Log.ERROR || priority == Log.WARN) {
            //YourCrashLibrary.log(priority, tag, message);
            Timber.e("Exception thrown");
        }
    }
}
