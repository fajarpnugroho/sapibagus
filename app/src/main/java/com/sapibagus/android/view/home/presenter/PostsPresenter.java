package com.sapibagus.android.view.home.presenter;

import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.api.model.response.CategoryPostsResponse;
import com.sapibagus.android.api.model.response.RecentPostsResponse;
import com.sapibagus.android.api.service.StreamServices;
import com.sapibagus.android.view.home.PostsView;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class PostsPresenter {

    private final StreamServices streamServices;

    private PostsView view;
    private PostNavigator navigator;

    @Inject
    public PostsPresenter(StreamServices streamServices) {
        this.streamServices = streamServices;
    }

    public void init(PostsView view, PostNavigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    public void fetchCategoryPosts(String slug) {
        if (slug.equalsIgnoreCase("home")) {
            Call<RecentPostsResponse> call = streamServices.recentPosts(null, null);
            call.enqueue(new Callback<RecentPostsResponse>() {
                @Override
                public void onResponse(Response<RecentPostsResponse> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        RecentPostsResponse recentPostsResponse = response.body();
                        view.showRecentPost(recentPostsResponse);
                    } else {
                        view.showEmpty();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    view.showError(t);
                }
            });
        } else {
            Call<CategoryPostsResponse> call = streamServices.categoryPosts(slug);
            call.enqueue(new Callback<CategoryPostsResponse>() {
                @Override
                public void onResponse(Response<CategoryPostsResponse> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        CategoryPostsResponse categoryPostsResponse = response.body();
                        view.showListPosts(categoryPostsResponse);
                    } else {
                        view.showEmpty();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    view.showError(t);
                }
            });
        }
    }

    public void navigateDetail(PostEntity postEntity) {
        navigator.navigateToDetail(postEntity);
    }
}
