package com.sapibagus.android.api.model.response;

import com.sapibagus.android.api.model.entity.PostEntity;

import java.util.List;

public final class RecentPostsResponse {
    public final String status;
    public final int count;
    public final int countTotal;
    public final int pages;
    public final List<PostEntity> posts;

    public RecentPostsResponse(String status, int count, int countTotal, int pages,
                               List<PostEntity> posts) {
        this.status = status;
        this.count = count;
        this.countTotal = countTotal;
        this.pages = pages;
        this.posts = posts;
    }
}
