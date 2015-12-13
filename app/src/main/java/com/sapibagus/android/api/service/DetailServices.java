package com.sapibagus.android.api.service;

import com.sapibagus.android.api.model.response.DetailPostResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface DetailServices {

    @GET("api/get_post")
    Call<DetailPostResponse> getPost(@Query("post_id") Integer postId, @Query("slug") String slug);
}
