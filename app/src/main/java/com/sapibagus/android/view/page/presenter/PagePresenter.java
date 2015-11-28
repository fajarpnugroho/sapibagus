package com.sapibagus.android.view.page.presenter;

import com.sapibagus.android.api.model.response.PageResponse;
import com.sapibagus.android.api.service.PageServices;
import com.sapibagus.android.view.page.PageView;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class PagePresenter {

    private final PageServices services;

    private PageView view;

    @Inject
    public PagePresenter(PageServices services) {
        this.services = services;
    }

    public void init(PageView view) {
        this.view = view;
    }

    public void loadPage(String pageName) {
        view.onLoading(true);

        Call<PageResponse> call = services.getPage(pageName);
        call.enqueue(new Callback<PageResponse>() {
            @Override
            public void onResponse(Response<PageResponse> response, Retrofit retrofit) {
                view.onLoading(false);

                if (response.body() != null) {
                    PageResponse pageResponse = response.body();
                    view.showPageContent(pageResponse);
                } else {
                    view.showEmpty();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                view.onLoading(false);
                view.showError(t);
            }
        });
    }
}
