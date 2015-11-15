package com.sapibagus.android.view.home.widget;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sapibagus.android.R;
import com.sapibagus.android.view.home.decoration.DividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListPostView extends FrameLayout {

    @Bind(R.id.recyclerview) RecyclerView recyclerView;
    @Bind(R.id.loading_view) LoadingView loadingView;
    @Bind(R.id.empty_view) TextView emptyView;

    public ListPostView(Context context) {
        this(context, null);
    }

    public ListPostView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListPostView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.posts_view, this);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void initView() {
        showLoading();
        hideEmpty();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        hideLoading();
        recyclerView.setVisibility(VISIBLE);
        recyclerView.setAdapter(adapter);
    }

    public void showLoading() { loadingView.show(); }

    public void hideLoading() { loadingView.hide(); }

    public void showEmpty() { emptyView.setVisibility(VISIBLE); }

    public void hideEmpty() { emptyView.setVisibility(GONE); }
}
