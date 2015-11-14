package com.sapibagus.android.api.model.response;

import com.sapibagus.android.api.model.entity.CategoryEntity;

import java.util.List;

public final class CategoriesResponse {
    public final List<CategoryEntity> data;

    public CategoriesResponse(List<CategoryEntity> data) {
        this.data = data;
    }
}
