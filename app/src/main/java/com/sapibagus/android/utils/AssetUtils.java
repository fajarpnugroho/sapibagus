package com.sapibagus.android.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class AssetUtils {
    private AssetUtils() {}

    public static Reader readFile(Context context, String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        return new InputStreamReader(inputStream, "utf-8");
    }

    public static Typeface getTypeface(Context context, String fontName) {
        return Typeface.createFromAsset(context.getAssets(), fontName);
    }
}
