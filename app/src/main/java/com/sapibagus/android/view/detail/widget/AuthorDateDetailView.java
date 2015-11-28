package com.sapibagus.android.view.detail.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.AuthorEntity;
import com.sapibagus.android.utils.DateUtils;
import com.sapibagus.android.view.home.widget.AuthorDateView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AuthorDateDetailView extends FrameLayout {

    @Bind(R.id.author_detail) TextView author;
    @Bind(R.id.source_date_view) AuthorDateView sourceDateView;

    public AuthorDateDetailView(Context context) {
        this(context, null);
    }

    public AuthorDateDetailView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AuthorDateDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.author_date_detail_view, this);
        ButterKnife.bind(this);
    }

    public void bind(AuthorEntity authorEntity, String date) {
        author.setText(authorEntity.name);
        sourceDateView.bind(authorEntity.url, DateUtils.parseTimeAgo(date).toString());
    }
}
