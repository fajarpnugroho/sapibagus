package com.sapibagus.android.view.home;

import com.sapibagus.android.api.model.response.CategoryPostsResponse;

public interface PostsView {
    void showListPosts(CategoryPostsResponse categoryPostsResponse);

    void showError(Throwable t);

    void showEmpty();
}
