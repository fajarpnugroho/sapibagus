package com.sapibagus.android.view.page;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.sapibagus.android.Injector;
import com.sapibagus.android.R;
import com.sapibagus.android.api.model.response.PageResponse;
import com.sapibagus.android.view.BaseActivity;
import com.sapibagus.android.view.home.presenter.MainNavigator;
import com.sapibagus.android.view.home.widget.ListPostView;
import com.sapibagus.android.view.page.adapter.PageAdapter;
import com.sapibagus.android.view.page.presenter.PagePresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PageActivity extends BaseActivity implements PageView {

    @Bind(R.id.list_post_view) ListPostView listPostView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Inject PagePresenter presenter;

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

        listPostView.initView();
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
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void showPageContent(PageResponse pageResponse) {
        PageAdapter adapter = new PageAdapter(pageResponse.page);
        listPostView.setAdapter(adapter);
    }

    @Override
    public void showError(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty() {
        listPostView.showEmpty();
    }

    @Override
    public void onLoading(boolean status) {
        if (status) {
            listPostView.showLoading();
        } else {
            listPostView.hideLoading();
        }
    }
}
