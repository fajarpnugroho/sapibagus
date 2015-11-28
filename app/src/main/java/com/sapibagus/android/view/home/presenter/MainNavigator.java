package com.sapibagus.android.view.home.presenter;

import android.app.Activity;
import android.content.Intent;

import com.sapibagus.android.view.page.PageActivity;

public class MainNavigator {

    public static final String EXTRA_PAGE_NAME = "extra_page_name";

    private final Activity activity;

    public MainNavigator(Activity activity) {
        this.activity = activity;
    }

    public void navigateToPage(String pageName) {
        Intent intent = new Intent(activity, PageActivity.class);
        intent.putExtra(EXTRA_PAGE_NAME, pageName);
        activity.startActivity(intent);
    }
}
