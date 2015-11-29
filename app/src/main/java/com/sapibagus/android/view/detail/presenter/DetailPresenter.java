package com.sapibagus.android.view.detail.presenter;

import com.sapibagus.android.api.model.response.DetailPostResponse;
import com.sapibagus.android.api.service.DetailServices;
import com.sapibagus.android.view.detail.DetailView;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DetailPresenter {

    private final DetailServices services;
    private DetailView view;
    private DetailNavigator navigator;

    @Inject
    public DetailPresenter(DetailServices services) {
        this.services = services;
    }

    public void init(DetailView view, DetailNavigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    public void getContentDetail(int postId) {
        view.onLoading(true);

        Call<DetailPostResponse> call = services.getPost(postId);
        call.enqueue(new Callback<DetailPostResponse>() {
            @Override
            public void onResponse(Response<DetailPostResponse> response, Retrofit retrofit) {
                view.onLoading(false);

                if (response.body() != null) {
                    DetailPostResponse postResponse = response.body();
                    view.showListPost(postResponse);
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

    public void share(String url) {
        navigator.shareAction(url);
    }
}
