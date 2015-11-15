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
import com.sapibagus.android.api.model.response.CategoryPostsResponse;
import com.sapibagus.android.view.home.PostsView;
import com.sapibagus.android.view.home.adapter.PostsAdapter;
import com.sapibagus.android.view.home.presenter.PostsPresenter;
import com.sapibagus.android.view.home.widget.ListPostView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class PostsFragment extends BaseFragment implements PostsView {

    public static final String ARG_SLUG = "arg_slug";
    @Bind(R.id.list_post_view) ListPostView listPostView;

    @Inject PostsPresenter presenter;

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
        Injector.INSTANCE.getApplicationComponent().inject(this);
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
        presenter.initView(this);
        listPostView.initView();
        presenter.fetchCategoryPosts(getArguments().getString(ARG_SLUG));
    }

    @Override
    public void showListPosts(CategoryPostsResponse categoryPostsResponse) {
        PostsAdapter adapter = new PostsAdapter();
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
}
