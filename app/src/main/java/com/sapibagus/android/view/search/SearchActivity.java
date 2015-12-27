package com.sapibagus.android.view.search;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sapibagus.android.Injector;
import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.api.model.response.SearchResultsResponse;
import com.sapibagus.android.view.BaseActivity;
import com.sapibagus.android.view.home.adapter.PostsAdapter;
import com.sapibagus.android.view.home.widget.ListPostView;
import com.sapibagus.android.view.listener.ListScrollListener;
import com.sapibagus.android.view.search.presenter.SearchNavigator;
import com.sapibagus.android.view.search.presenter.SearchPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchActivityView,
        SearchView.OnQueryTextListener, PostsAdapter.Listener {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.list_post_view) ListPostView listPostView;

    @Inject SearchPresenter presenter;

    private PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.INSTANCE.getContextComponent().inject(this);

        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initToolbar();

        presenter.init(this, new SearchNavigator(this));

        listPostView.hideLoading();
        listPostView.addScrollListener(new ListScrollListener() {
            @Override
            public void loadMore(int page) {
                presenter.loadMore(page);
            }
        });
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() == null) {
            throw new IllegalStateException("Activity must set a support toolbar");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.expandActionView();

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search article");
        searchView.setOnQueryTextListener(this);
        return super.onPrepareOptionsMenu(menu);
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
    public void onBackPressed() {
        finish();
    }

    @Override
    public void showSearchResult(SearchResultsResponse resultsResponse) {
        if (resultsResponse.count > 0) {
            adapter = new PostsAdapter(this);
            adapter.setPosts(resultsResponse.posts);
            listPostView.setAdapter(adapter);
        }
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
    public void showLoading() {
        listPostView.showLoading();
    }

    @Override
    public void hideLoading() {
        listPostView.hideLoading();
    }

    @Override
    public void moreSearchResult(SearchResultsResponse resultsResponse) {
        if (adapter != null) {
            adapter.moreListPosts(resultsResponse.posts);
        }
    }

    @Override
    public void noMoreResult() {
        if (adapter != null) {
            adapter.noLoadMore();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!TextUtils.isEmpty(query)) {
            presenter.search(query, null);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void itemClickListener(PostEntity postEntity) {
        presenter.navigateToDetailActivity(postEntity);
    }
}
