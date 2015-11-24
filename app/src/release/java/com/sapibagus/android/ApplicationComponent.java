package com.sapibagus.android;

import com.sapibagus.android.analytic.AnalyticModule;
import com.sapibagus.android.analytic.ReleaseAnalyticModule;
import com.sapibagus.android.api.ApiModule;
import com.sapibagus.android.view.detail.DetailActivity;
import com.sapibagus.android.view.home.MainActivity;
import com.sapibagus.android.view.home.fragment.PostsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {ApplicationContextModule.class, ApiModule.class,
        ReleaseAnalyticModule.class})
public interface ApplicationComponent extends ApplicationContextComponent {
        void inject(SapiBagusApp sapiBagusApp);

        void inject(PostsFragment postsFragment);

        void inject(MainActivity mainActivity);

        void inject(DetailActivity detailActivity);
}
