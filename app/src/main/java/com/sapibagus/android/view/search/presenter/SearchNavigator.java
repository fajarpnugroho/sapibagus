package com.sapibagus.android.view.search.presenter;

import android.app.Activity;
import android.content.Intent;

import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.view.detail.DetailActivity;

public class SearchNavigator {
    private final Activity activity;

    public SearchNavigator(Activity activity) {
        this.activity = activity;
    }

    public void navigateToDetail(PostEntity postEntity) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POST_ID, postEntity.id);
        intent.putExtra(DetailActivity.EXTRA_URL, postEntity.url);
        activity.startActivity(intent);
    }
}
