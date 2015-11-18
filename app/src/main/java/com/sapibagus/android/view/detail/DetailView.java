package com.sapibagus.android.view.detail;

import com.sapibagus.android.api.model.response.DetailPostResponse;

public interface DetailView {
    void initToolbar();

    void showListPost(DetailPostResponse detailPostResponse);

    void showError(Throwable t);

    void showEmpty();

    void onLoading(boolean status);


}
