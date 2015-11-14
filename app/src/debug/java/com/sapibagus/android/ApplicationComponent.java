package com.sapibagus.android;

import com.sapibagus.android.analytic.AnalyticModule;
import com.sapibagus.android.analytic.DebugAnalyticModule;
import com.sapibagus.android.api.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {ApplicationContextModule.class, ApiModule.class, DebugAnalyticModule.class})
public interface ApplicationComponent extends ApplicationContextComponent {
}
