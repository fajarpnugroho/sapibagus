package com.sapibagus.android;

import android.app.Application;

public enum Injector {
    INSTANCE;

    Injector() {}

    private ApplicationComponent applicationComponent;

    void initializeComponent(Application application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationContextModule(new ApplicationContextModule(application)).build();
    }

    public ApplicationComponent getApplicationComponent() { return applicationComponent; }
}
