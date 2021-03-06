package com.sapibagus.android.api.service;

import com.sapibagus.android.api.model.response.CategoryPostsResponse;
import com.sapibagus.android.api.model.response.RecentPostsResponse;
import com.sapibagus.android.api.model.response.SearchResultsResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface StreamServices {

    @GET("api/get_recent_posts")
    Call<RecentPostsResponse> recentPosts(@Query("page") Integer page,
                                          @Query("count") Integer count);

    @GET("api/get_category_posts")
    Call<CategoryPostsResponse> categoryPosts(@Query("slug") String slug,
                                              @Query("page") Integer page);

    @GET("api/get_search_results")
    Call<SearchResultsResponse> searchResults(@Query("search") String search,
                                              @Query("page") Integer page);

}
