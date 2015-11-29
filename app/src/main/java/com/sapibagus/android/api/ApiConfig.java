package com.sapibagus.android.api;

import android.content.Context;

import com.squareup.okhttp.Cache;

import java.io.File;

public final class ApiConfig {

    public ApiConfig() {}

    public static final String API_ENDPOINT = "http://www.sapibagus.com/";

    public static final String BITLY_ACCESS_TOKEN = "c516ce812744e96a4c6374a7cc12905949c78422";

    public static final String BITLY_API_KEY = "R_c466cf9cced84572b0e1630fa1cf9195";

    public static Cache createCache(Context context, String cacheDirName, long sizeInByte) {
        File cacheDir = new File(context.getCacheDir(), cacheDirName);
        return new Cache(cacheDir, sizeInByte);
    }
}
