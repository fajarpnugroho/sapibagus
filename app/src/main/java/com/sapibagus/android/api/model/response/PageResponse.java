package com.sapibagus.android.api.model.response;

import com.sapibagus.android.api.model.entity.PageEntity;

import java.util.List;

public final class PageResponse {
    public final String status;
    public final List<PageEntity> page;

    public PageResponse(String status, List<PageEntity> page) {
        this.status = status;
        this.page = page;
    }
}
