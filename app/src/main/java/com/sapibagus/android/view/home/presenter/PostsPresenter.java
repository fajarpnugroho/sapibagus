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

    public void fetchPosts(String slug, final Integer page) {
        if (slug.equalsIgnoreCase("home")) {
            Call<RecentPostsResponse> call = streamServices.recentPosts(page, null);
            call.enqueue(new Callback<RecentPostsResponse>() {
                @Override
                public void onResponse(Response<RecentPostsResponse> response, Retrofit retrofit) {
                    if (page == null) {
                        showRecentPosts(response.body());
                    } else {
                        moreRecentPosts(response.body());
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    view.showError(t);
                }
            });
        } else {
            Call<CategoryPostsResponse> call = streamServices.categoryPosts(slug, page);
            call.enqueue(new Callback<CategoryPostsResponse>() {
                @Override
                public void onResponse(Response<CategoryPostsResponse> response, Retrofit retrofit) {
                    if (page == null) {
                        showListPosts(response.body());
                    } else {
                        moreListPosts(response.body());
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    view.showError(t);
                }
            });
        }
    }

    public void showRecentPosts(RecentPostsResponse recentPostsResponse) {
        if (recentPostsResponse != null) {
            view.showRecentPost(recentPostsResponse);
        } else {
            view.showEmpty();
        }
    }

    public void showListPosts(CategoryPostsResponse categoryPostsResponse) {
        if (categoryPostsResponse != null) {
            view.showListPosts(categoryPostsResponse);
        } else {
            view.showEmpty();
        }
    }

    public void moreRecentPosts(RecentPostsResponse recentPostsResponse) {
        if (recentPostsResponse.count > 0) {
            view.moreRecentPosts(recentPostsResponse);
        } else {
            view.noMorePosts();
        }
    }

    public void moreListPosts(CategoryPostsResponse categoryPostsResponse) {
        if (categoryPostsResponse.count > 0) {
            view.moreListPosts(categoryPostsResponse);
        } else {
            view.noMorePosts();
        }
    }

    public void navigateDetail(PostEntity postEntity) {
        navigator.navigateToDetail(postEntity);
    }
}
