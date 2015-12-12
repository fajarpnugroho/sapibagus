package com.sapibagus.android.view.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sapibagus.android.Injector;
import com.sapibagus.android.R;
import com.sapibagus.android.analytic.AnalyticManager;
import com.sapibagus.android.analytic.AnalyticTracker;
import com.sapibagus.android.api.model.response.DetailPostResponse;
import com.sapibagus.android.view.BaseActivity;
import com.sapibagus.android.view.detail.presenter.DetailNavigator;
import com.sapibagus.android.view.detail.presenter.DetailPresenter;
import com.sapibagus.android.view.detail.widget.WebLoadingView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements DetailView, AnalyticTracker,
        WebLoadingView.Listener {

    public static final String EXTRA_POST_ID = "extra_post_id";
    public static final String EXTRA_CONTENT = "extra_content";
    public static final String EXTRA_URL = "extra_url";

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.web_view) WebLoadingView webView;

    @Inject DetailPresenter presenter;
    @Inject AnalyticManager analyticManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.INSTANCE.getContextComponent().inject(this);

        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        initToolbar();

        presenter.init(this, new DetailNavigator(this));

        if (getIntent().getExtras() == null) {
            throw new IllegalStateException("Activity start without provide post id");
        }

        presenter.getContentDetail(getIntent().getExtras().getInt(EXTRA_POST_ID));

        webView.setListener(this);
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() == null) return;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_share:
                if (getIntent().getExtras() == null) {
                    throw new IllegalStateException("Acitivity start without provide url");
                }

                String shareUrl = getIntent().getExtras().getString(EXTRA_URL);

                trackEvent("Detail", "Share Article", shareUrl);

                presenter.share(shareUrl);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showListPost(DetailPostResponse detailPostResponse) {
        trackScreen("Detail/" + detailPostResponse.post.titlePlain);

        webView.bind(detailPostResponse.post.titlePlain, detailPostResponse.post.content);
    }

    @Override
    public void showError(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmpty() {
    }

    @Override
    public void onLoading(boolean status) {
        webView.onLoading(status);
    }

    @Override
    public void trackScreen(String screenName) {
        analyticManager.sendScreen(screenName);
    }

    @Override
    public void trackEvent(String eventCategory, String eventAction, String eventLabel) {
        analyticManager.sendEvent(eventCategory, eventAction, eventLabel);
    }

    @Override
    public void openLinkInsideContent(String title, String url) {
        trackEvent("Detail/" + title, "Open Link", url);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
