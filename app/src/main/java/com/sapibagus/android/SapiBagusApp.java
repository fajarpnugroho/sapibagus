package com.sapibagus.android;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.sapibagus.android.api.ApiConfig;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class SapiBagusApp extends Application {

    private static final long PICASSO_CACHE_SIZE_IN_BYTE = 100 * 1024 * 1024; // 100 MB

    @Override
    public void onCreate() {
        super.onCreate();

        Injector.INSTANCE.initializeComponent(this);

        // Crashlytics
        Fabric.with(this, new Crashlytics());

        // Log
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            Crashlytics.log(priority, tag, message);

            if (t != null) {
                Crashlytics.logException(t);
            }
        }
    }
}
