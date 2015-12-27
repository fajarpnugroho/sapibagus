package com.sapibagus.android.view.search;

import com.sapibagus.android.api.model.response.SearchResultsResponse;

public interface SearchActivityView {
    void initToolbar();

    void showSearchResult(SearchResultsResponse resultsResponse);

    void showError(Throwable t);

    void showEmpty();

    void showLoading();

    void hideLoading();

    void moreSearchResult(SearchResultsResponse resultsResponse);

    void noMoreResult();
}
