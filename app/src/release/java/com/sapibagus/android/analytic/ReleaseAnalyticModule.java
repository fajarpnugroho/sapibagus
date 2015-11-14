package com.sapibagus.android.analytic;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ReleaseAnalyticModule extends AnalyticModule {

    @Provides
    @Singleton
    AnalyticProperty provideAnalyticProperty() {
        return AnalyticProperty.PRODUCTION;
    }

    @Provides
    @Singleton
    Tracker provideTracker(GoogleAnalytics googleAnalytics, AnalyticProperty analyticProperty) {
        Tracker tracker = googleAnalytics.newTracker(analyticProperty.getValue());
        tracker.enableExceptionReporting(true);
        tracker.enableAutoActivityTracking(false);
        return tracker;
    }
}
