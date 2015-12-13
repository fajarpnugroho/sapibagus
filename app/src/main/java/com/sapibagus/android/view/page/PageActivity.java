package com.sapibagus.android.view.page;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.sapibagus.android.Injector;
import com.sapibagus.android.R;
import com.sapibagus.android.analytic.AnalyticManager;
import com.sapibagus.android.analytic.AnalyticTracker;
import com.sapibagus.android.api.model.response.PageResponse;
import com.sapibagus.android.view.BaseActivity;
import com.sapibagus.android.view.detail.widget.WebLoadingView;
import com.sapibagus.android.view.home.presenter.MainNavigator;
import com.sapibagus.android.view.page.presenter.PagePresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PageActivity extends BaseActivity implements PageView, AnalyticTracker {

    @Bind(R.id.web_view) WebLoadingView webView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Inject PagePresenter presenter;
    @Inject AnalyticManager analyticManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.INSTANCE.getContextComponent().inject(this);

        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        initToolbar();

        if (getIntent().getExtras() == null) {
            throw new IllegalStateException("Activity start without provide page name");
        }

        presenter.init(this);
        presenter.loadPage(getIntent().getExtras().getString(MainNavigator.EXTRA_PAGE_NAME));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
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
    public void showPageContent(PageResponse pageResponse) {
        trackScreen("Page/" + pageResponse.page.titlePlain);

        webView.bind(pageResponse.page.titlePlain, pageResponse.page.content);
    }

    @Override
    public void showError(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
}
