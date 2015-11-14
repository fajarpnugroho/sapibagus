package com.sapibagus.android.api.service;

import com.sapibagus.android.api.model.response.PageResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface PageServices {

    @GET("api/get_page")
    Call<PageResponse> getPage(@Query("slug") String slug);
}
