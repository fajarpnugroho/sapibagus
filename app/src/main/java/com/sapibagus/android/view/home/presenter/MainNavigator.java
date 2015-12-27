package com.sapibagus.android.view.home.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.sapibagus.android.R;
import com.sapibagus.android.view.search.SearchActivity;

public class MainNavigator {

    public static final String EXTRA_PAGE_NAME = "extra_page_name";
    public static final String TEL_FORMAT_DATA = "tel:%s";
    public static final String TOKO_SAPIBAGUS = "http://toko.sapibagus.com";

    private final Activity activity;

    public MainNavigator(Activity activity) {
        this.activity = activity;
    }

    public void navigateToPhoneDial() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(String.format(TEL_FORMAT_DATA,
                activity.getString(R.string.phone_number))));
        activity.startActivity(intent);
    }

    public void openToko() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(TOKO_SAPIBAGUS));
        activity.startActivity(intent);
    }

    public void navigateToSearchActivity() {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }
}
