package com.sapibagus.android.view.home;

import com.sapibagus.android.api.model.response.CategoryPostsResponse;
import com.sapibagus.android.api.model.response.RecentPostsResponse;

public interface PostsView {
    void showListPosts(CategoryPostsResponse categoryPostsResponse);

    void showError(Throwable t);

    void showEmpty();

    void showRecentPost(RecentPostsResponse recentPostsResponse);

    void moreRecentPosts(RecentPostsResponse recentPostsResponse);

    void noMorePosts();

    void moreListPosts(CategoryPostsResponse categoryPostsResponse);
}
