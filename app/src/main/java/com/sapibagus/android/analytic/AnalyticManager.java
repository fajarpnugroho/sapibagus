package com.sapibagus.android.analytic;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

public class AnalyticManager {

    private final Tracker tracker;

    @Inject
    public AnalyticManager(Tracker tracker) {
        this.tracker = tracker;
    }

    public void sendScreen(String screenName) {
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void sendEvent(String category, String action, String label) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build()
        );
    }
}
