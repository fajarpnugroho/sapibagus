package com.sapibagus.android.view.search.presenter;

import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.api.model.response.SearchResultsResponse;
import com.sapibagus.android.api.service.StreamServices;
import com.sapibagus.android.view.search.SearchActivityView;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchPresenter {

    @Inject StreamServices streamServices;

    private SearchActivityView view;
    private SearchNavigator navigator;
    private String keyword;

    @Inject
    public SearchPresenter() {}

    private Callback<SearchResultsResponse> searchResultsResponseCallback =
            new Callback<SearchResultsResponse>() {
        @Override
        public void onResponse(Response<SearchResultsResponse> response, Retrofit retrofit) {
            view.hideLoading();
            if (response.body() != null) {
                SearchResultsResponse resultsResponse = response.body();
                view.showSearchResult(resultsResponse);
            } else {
                view.showEmpty();
            }
        }

        @Override
        public void onFailure(Throwable t) {
            view.hideLoading();
            view.showError(t);
        }
    };

    private Callback<SearchResultsResponse> moreSearchResultResponseCallback =
            new Callback<SearchResultsResponse>() {
        @Override
        public void onResponse(Response<SearchResultsResponse> response, Retrofit retrofit) {
            if (response.body() != null) {
                SearchResultsResponse searchResultsResponse = response.body();
                if (searchResultsResponse.count > 0 ) {
                    view.moreSearchResult(searchResultsResponse);
                } else {
                    view.noMoreResult();
                }
            } else {
                view.noMoreResult();
            }
        }

        @Override
        public void onFailure(Throwable t) {
            view.showError(t);
        }
    };

    public void init(SearchActivityView view, SearchNavigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    public void search(String keyword, Integer page) {
        this.keyword = keyword;
        Call<SearchResultsResponse> call = streamServices.searchResults(keyword, page);
        if (page == null) {
            view.showLoading();
            call.enqueue(searchResultsResponseCallback);
        } else {
            call.enqueue(moreSearchResultResponseCallback);
        }

    }

    public void navigateToDetailActivity(PostEntity postEntity) {
        navigator.navigateToDetail(postEntity);
    }

    public void loadMore(int page) {
        search(keyword, page);
    }
}
