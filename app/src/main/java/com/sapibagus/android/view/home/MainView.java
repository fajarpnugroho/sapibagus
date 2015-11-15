package com.sapibagus.android.view.home;

import com.sapibagus.android.api.model.response.CategoriesResponse;

public interface MainView {

    void initToolbar();

    void initPager();

    void initFAB();

    void showCategories(CategoriesResponse response);
}
