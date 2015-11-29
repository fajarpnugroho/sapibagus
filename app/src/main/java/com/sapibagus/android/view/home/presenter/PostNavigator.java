package com.sapibagus.android.view.home.presenter;

import android.app.Activity;
import android.content.Intent;

import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.view.detail.DetailActivity;

public class PostNavigator {

    private final Activity activity;

    public PostNavigator(Activity activity) {
        this.activity = activity;
    }

    public void navigateToDetail(PostEntity postEntity) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POST_ID, (int) postEntity.id);
        intent.putExtra(DetailActivity.EXTRA_CONTENT, postEntity.content);
        intent.putExtra(DetailActivity.EXTRA_URL, postEntity.url);
        activity.startActivity(intent);
    }
}
