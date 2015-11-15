package com.sapibagus.android.view.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.sapibagus.android.R;

public class LoadingView extends FrameLayout {

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.loading_view, this);
    }

    public void show() { setVisibility(VISIBLE); }

    public void hide() { setVisibility(GONE); }
}
