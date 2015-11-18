package com.sapibagus.android.view.detail.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.AttachmentEntity;
import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.utils.AssetUtils;
import com.sapibagus.android.utils.ImageUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TitleView extends FrameLayout {

    @Bind(R.id.picture) ImageView picture;
    @Bind(R.id.title) TextView title;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.title_view, this);

        ButterKnife.bind(this);
    }

    public void bind(PostEntity postEntity) {
        AttachmentEntity attachmentEntity = ImageUtils
                .getImageAttachment(postEntity.attachments);

        if (attachmentEntity != null) {
            picture.setVisibility(VISIBLE);
            Picasso.with(getContext())
                    .load(attachmentEntity.url)
                    .fit()
                    .centerCrop()
                    .into(picture);
        } else {
            picture.setVisibility(GONE);
        }

        title.setText(postEntity.titlePlain);
        title.setTypeface(AssetUtils.getTypeface(getContext(), "fonts/Arvo-Regular.ttf"));

    }
}
