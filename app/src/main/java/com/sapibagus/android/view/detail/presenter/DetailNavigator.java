package com.sapibagus.android.view.detail.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.sapibagus.android.view.detail.DetailActivity;

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

    public void navigateToDetail(String url) {
        Uri uri = Uri.parse(url);

        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POST_SLUG, uri.getPathSegments().get(3));
        intent.putExtra(DetailActivity.EXTRA_URL, url);

        activity.startActivity(intent);
    }
}
