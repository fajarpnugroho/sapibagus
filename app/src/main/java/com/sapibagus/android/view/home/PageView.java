package com.sapibagus.android.view.home;

import com.sapibagus.android.api.model.response.DetailPostResponse;

public interface PageView {
    void showListPost(DetailPostResponse detailPostResponse);

    void showError(Throwable t);

    void showEmpty();

    void onLoading(boolean status);
}
