package com.sapibagus.android.view.home.presenter;

import com.sapibagus.android.api.model.response.CategoryPostsResponse;
import com.sapibagus.android.api.service.StreamServices;
import com.sapibagus.android.view.home.PostsView;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class PostsPresenter {

    private StreamServices streamServices;
    private PostsView view;

    @Inject
    public PostsPresenter(StreamServices streamServices) {
        this.streamServices = streamServices;
    }

    public void initView(PostsView view) {
        this.view = view;
    }

    public void fetchCategoryPosts(String slug) {
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
