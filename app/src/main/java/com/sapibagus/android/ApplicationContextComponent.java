package com.sapibagus.android;

import com.sapibagus.android.view.detail.DetailActivity;
import com.sapibagus.android.view.home.MainActivity;
import com.sapibagus.android.view.home.fragment.PageFragment;
import com.sapibagus.android.view.home.fragment.PostsFragment;
import com.sapibagus.android.view.page.PageActivity;

import javax.inject.Singleton;

import dagger.Component;

public interface ApplicationContextComponent {
    void inject(SapiBagusApp sapiBagusApp);

    void inject(PostsFragment postsFragment);

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);

    void inject(PageActivity pageActivity);

    void inject(PageFragment pageFragment);
}
