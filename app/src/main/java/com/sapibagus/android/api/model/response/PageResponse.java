package com.sapibagus.android.api.model.response;

import com.sapibagus.android.api.model.entity.PageEntity;

public final class PageResponse {
    public final String status;
    public final PageEntity page;

    public PageResponse(String status, PageEntity page) {
        this.status = status;
        this.page = page;
    }
}
