package com.sapibagus.android.view.home.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.sapibagus.android.R;

public class MainNavigator {

    public static final String EXTRA_PAGE_NAME = "extra_page_name";
    public static final String TEL_FORMAT_DATA = "tel:%s";

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
}
