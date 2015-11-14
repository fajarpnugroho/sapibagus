package com.sapibagus.android.api.model.response;

import com.sapibagus.android.api.model.entity.PostEntity;

public final class DetailPostResponse {
    public final String status;
    public final PostEntity post;
    public final String previousUrl;

    public DetailPostResponse(String status, PostEntity post, String previousUrl) {
        this.status = status;
        this.post = post;
        this.previousUrl = previousUrl;
    }
}
