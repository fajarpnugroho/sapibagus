package com.sapibagus.android.view.detail.presenter;

import android.app.Activity;
import android.content.Intent;

public class DetailNavigator {
    private final Activity activity;

    public DetailNavigator(Activity activity) {
        this.activity = activity;
    }

    public void shareAction(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, url);
        intent.setType("text/plain");
        activity.startActivity(intent);
    }
}
