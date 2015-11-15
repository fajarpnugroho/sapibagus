package com.sapibagus.android.view.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.TagEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TagsView extends FrameLayout {

    @Bind(R.id.tags) LinearLayout root;

    public TagsView(Context context) {
        this(context, null);
    }

    public TagsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.tags_view, this);
        ButterKnife.bind(this);
    }

    public void bind(List<TagEntity> tags) {
        // reset child view
        root.removeAllViews();

        if (tags.size() > 0) {

            int i = 0;

            for (TagEntity tagEntity : tags) {

                if (i > 2) { break; }

                i++;

                ItemTagView itemTagView = new ItemTagView(getContext());
                itemTagView.bind(tagEntity.title);
                root.addView(itemTagView);
            }
        }
    }
}
