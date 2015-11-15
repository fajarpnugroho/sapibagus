package com.sapibagus.android.view.home.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sapibagus.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemTagView extends FrameLayout {

    @Bind(R.id.tag_label) TextView label;

    public ItemTagView(Context context) {
        this(context, null);
    }

    public ItemTagView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemTagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.item_tag_view, this);
        ButterKnife.bind(this);
    }

    public void bind(String tag) {
        label.setText(tag);
        label.setTypeface(getTypeface("fonts/RobotoSlab-Thin.ttf"));
    }

    private Typeface getTypeface(String fontName) {
        return Typeface.createFromAsset(getContext().getAssets(), fontName);
    }
}
