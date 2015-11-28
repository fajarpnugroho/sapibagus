package com.sapibagus.android.view.page;

import com.sapibagus.android.api.model.response.PageResponse;

public interface PageView {
    void initToolbar();

    void showPageContent(PageResponse pageResponse);

    void showError(Throwable t);

    void showEmpty();

    void onLoading(boolean status);
}
