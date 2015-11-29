package com.sapibagus.android.view.home.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.sapibagus.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoadingView extends FrameLayout {

    @Bind(R.id.root) FrameLayout root;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.loading_view, this);
        ButterKnife.bind(this);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.Loading, 0, 0);

        if (a.getBoolean(R.styleable.Loading_intermediateProgressBar, false)) {
            addHorizontalProgressBar(context);
        } else {
            addCircleProgressBar(context);
        }

        a.recycle();
    }

    private void addCircleProgressBar(Context context) {
        ProgressBar progressBar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleLarge);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        progressBar.setLayoutParams(params);
        addView(progressBar);
    }

    private void addHorizontalProgressBar(Context context) {
        ProgressBar progressBar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressBar.setIndeterminate(true);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(params);

        addView(progressBar);
    }

    public void show() { setVisibility(VISIBLE); }

    public void hide() { setVisibility(GONE); }
}
