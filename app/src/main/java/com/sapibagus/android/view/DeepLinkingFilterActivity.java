package com.sapibagus.android.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sapibagus.android.view.detail.DetailActivity;
import com.sapibagus.android.view.home.MainActivity;

import java.util.List;

public class DeepLinkingFilterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleExtra(getIntent().getData());
    }

    private void handleExtra(@Nullable Uri data) {
        if (data == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        List<String> link = data.getPathSegments();

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POST_SLUG, link.get(3));
        intent.putExtra(DetailActivity.EXTRA_URL, data.toString());
        startActivity(intent);

        finish();
    }
}
