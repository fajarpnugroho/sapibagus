package com.sapibagus.android;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationContextModule {
    private final Application application;

    public ApplicationContextModule(Application application) { this.application = application; }

    @Provides
    @Singleton
    Application provideApplication() { return application; }

    @Provides
    @Singleton
    Context provideContext() { return application; }
}
