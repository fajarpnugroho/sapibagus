package com.sapibagus.android.analytic;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AnalyticModule {

    public static final int DISPATCH_PERIOD_IN_SECONDS = 1800;

    @Provides
    @Singleton
    GoogleAnalytics provideGoogleAnalytics(Application application) {
        GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(application);
        googleAnalytics.setLocalDispatchPeriod(DISPATCH_PERIOD_IN_SECONDS);
        return googleAnalytics;
    }
}
