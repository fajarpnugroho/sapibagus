package com.sapibagus.android;

import com.sapibagus.android.analytic.AnalyticModule;
import com.sapibagus.android.analytic.ReleaseAnalyticModule;
import com.sapibagus.android.api.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {ApplicationContextModule.class, ApiModule.class,
        ReleaseAnalyticModule.class})
public interface ApplicationComponent extends ApplicationContextComponent {
}
