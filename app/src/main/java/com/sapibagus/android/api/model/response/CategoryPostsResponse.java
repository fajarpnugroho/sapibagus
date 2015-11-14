package com.sapibagus.android.api.model.response;

import com.sapibagus.android.api.model.entity.CategoryEntity;
import com.sapibagus.android.api.model.entity.PostEntity;

import java.util.List;

public final class CategoryPostsResponse {
    public final String status;
    public final int count;
    public final int pages;
    public final CategoryEntity category;
    public final List<PostEntity> posts;

    public CategoryPostsResponse(String status, int count, int pages, CategoryEntity category,
                                 List<PostEntity> posts) {
        this.status = status;
        this.count = count;
        this.pages = pages;
        this.category = category;
        this.posts = posts;
    }
}
