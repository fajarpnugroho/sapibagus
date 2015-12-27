package com.sapibagus.android.api.model.response;

import com.sapibagus.android.api.model.entity.PostEntity;

import java.util.List;

public final class SearchResultsResponse {
    public final String status;
    public final int count;
    public final int pages;
    public final List<PostEntity> posts;

    public SearchResultsResponse(String status, int count, int pages, List<PostEntity> posts) {
        this.status = status;
        this.count = count;
        this.pages = pages;
        this.posts = posts;
    }
}
