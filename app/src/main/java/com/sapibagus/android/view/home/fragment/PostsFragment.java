package com.sapibagus.android.view.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sapibagus.android.Injector;
import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.api.model.response.CategoryPostsResponse;
import com.sapibagus.android.api.model.response.RecentPostsResponse;
import com.sapibagus.android.view.home.PostsView;
import com.sapibagus.android.view.home.adapter.PostsAdapter;
import com.sapibagus.android.view.home.presenter.PostNavigator;
import com.sapibagus.android.view.home.presenter.PostsPresenter;
import com.sapibagus.android.view.home.widget.ListPostView;
import com.sapibagus.android.view.listener.ListScrollListener;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostsFragment extends BaseFragment implements PostsView, PostsAdapter.Listener {

    public static final String ARG_SLUG = "arg_slug";
    @Bind(R.id.list_post_view) ListPostView listPostView;

    @Inject PostsPresenter presenter;

    private PostsAdapter adapter;
    private int pages;

    public PostsFragment() {}

    public static PostsFragment newInstance(String slug) {
        Bundle args = new Bundle();
        args.putString(ARG_SLUG, slug);

        PostsFragment fragment = new PostsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Injector.INSTANCE.getContextComponent().inject(this);
        adapter = new PostsAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(this, new PostNavigator(getActivity()));
        listPostView.initView();
        presenter.fetchPosts(getArguments().getString(ARG_SLUG), null);

        listPostView.addScrollListener(new ListScrollListener() {
            @Override
            public void loadMore(int page) {
                presenter.fetchPosts(getArguments().getString(ARG_SLUG), page);
            }
        });
    }

    @Override
    public void showListPosts(CategoryPostsResponse categoryPostsResponse) {
        adapter.setPosts(categoryPostsResponse.posts);
        listPostView.setAdapter(adapter);
    }

    @Override
    public void showError(Throwable t) {
        listPostView.hideLoading();
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmpty() {
        listPostView.hideLoading();
        listPostView.showEmpty();
    }

    @Override
    public void showRecentPost(RecentPostsResponse recentPostsResponse) {
        adapter.setPosts(recentPostsResponse.posts);
        listPostView.setAdapter(adapter);
    }

    @Override
    public void moreRecentPosts(RecentPostsResponse recentPostsResponse) {
        if (adapter != null) {
            adapter.moreListPosts(recentPostsResponse.posts);
        }
    }

    @Override
    public void noMorePosts() {
        adapter.noLoadMore();
    }

    @Override
    public void moreListPosts(CategoryPostsResponse categoryPostsResponse) {
        if (adapter != null) {
            adapter.moreListPosts(categoryPostsResponse.posts);
        }
    }

    @Override
    public void itemClickListener(PostEntity postEntity) {
        presenter.navigateDetail(postEntity);
    }
}
