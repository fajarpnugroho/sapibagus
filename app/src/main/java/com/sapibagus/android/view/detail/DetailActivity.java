package com.sapibagus.android.view.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.sapibagus.android.Injector;
import com.sapibagus.android.R;
import com.sapibagus.android.api.model.response.DetailPostResponse;
import com.sapibagus.android.view.BaseActivity;
import com.sapibagus.android.view.detail.adapter.DetailAdapter;
import com.sapibagus.android.view.detail.presenter.DetailPresenter;
import com.sapibagus.android.view.home.widget.ListPostView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements DetailView {

    public static final String EXTRA_POST_ID = "extra_post_id";
    public static final String EXTRA_CONTENT = "extra_content";

    @Bind(R.id.list_post_view) ListPostView listPostView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Inject DetailPresenter presenter;

    private DetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.INSTANCE.getApplicationComponent().inject(this);

        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        initToolbar();

        presenter.initView(this);

        if (getIntent().getExtras() == null) {
            return;
        }

        presenter.getContentDetail(getIntent().getExtras().getInt(EXTRA_POST_ID));
        listPostView.initView();
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
    public void showListPost(DetailPostResponse detailPostResponse) {
        adapter = new DetailAdapter(detailPostResponse.post);
        listPostView.setAdapter(adapter);
    }

    @Override
    public void showError(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
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
