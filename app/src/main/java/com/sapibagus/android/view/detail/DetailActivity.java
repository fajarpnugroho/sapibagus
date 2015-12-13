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
    public static final String EXTRA_POST_SLUG = "extra_post_slug";

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

        handleIntent();

        webView.setListener(this);
    }

    private void handleIntent() {
        if (getIntent().getExtras().containsKey(EXTRA_POST_ID)) {
            presenter.getContentDetail(getIntent().getExtras().getInt(EXTRA_POST_ID), null);
        } else if (getIntent().getExtras().containsKey(EXTRA_POST_SLUG)) {
            presenter.getContentDetail(null, getIntent().getExtras().getString(EXTRA_POST_SLUG));
        } else {
            throw new IllegalStateException("Activity start without provide post id or slug name");
        }
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() == null) return;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Home");
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

    @Override
    public void openRelatedArticle(String title, String url) {
        trackEvent("Detail/" + title, "Open Related", url);

        presenter.navigateToRelatedArticle(url);
    }
}
