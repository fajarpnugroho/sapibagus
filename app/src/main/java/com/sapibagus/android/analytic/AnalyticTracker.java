package com.sapibagus.android.analytic;

public interface AnalyticTracker {
    void trackScreen(String screenName);

    void trackEvent(String eventCategory, String eventAction, String eventLabel);
}
