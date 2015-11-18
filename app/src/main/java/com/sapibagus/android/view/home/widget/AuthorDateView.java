package com.sapibagus.android.view.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.utils.DateUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AuthorDateView extends FrameLayout {

    @Bind(R.id.author) TextView author;
    @Bind(R.id.date) TextView date;

    public AuthorDateView(Context context) {
        this(context, null);
    }

    public AuthorDateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AuthorDateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.author_date_list_view, this);
        ButterKnife.bind(this);
    }

    public void bind(PostEntity postEntity) {
        author.setText(postEntity.author.name);
        date.setText(DateUtils.parseTimeAgo(postEntity.date).toString());
    }

    public void bind(String url, String dateText) {
        author.setText(url);
        date.setText(dateText);
    }
}
