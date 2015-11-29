package com.sapibagus.android;

import android.app.Application;

public enum Injector {
    INSTANCE;

    Injector() {}

    private ApplicationContextComponent contextComponent;

    void initializeComponent(Application application) {
        contextComponent = DaggerApplicationComponent.builder()
                .applicationContextModule(new ApplicationContextModule(application)).build();
    }

    public ApplicationContextComponent getContextComponent() { return contextComponent; }
}
