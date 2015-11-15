package com.sapibagus.android.api;

import android.content.Context;

import com.squareup.okhttp.Cache;

import java.io.File;

public final class ApiConfig {

    public ApiConfig() {}

    public static final String API_ENDPOINT = "http://www.sapibagus.com/";

    public static Cache createCache(Context context, String cacheDirName, long sizeInByte) {
        File cacheDir = new File(context.getCacheDir(), cacheDirName);
        return new Cache(cacheDir, sizeInByte);
    }
}
