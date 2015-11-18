package com.sapibagus.android.view.detail.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.view.detail.widget.AuthorDateDetailView;
import com.sapibagus.android.view.detail.widget.ImageGetter;
import com.sapibagus.android.view.detail.widget.TitleView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.xml.sax.XMLReader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_TYPE = 0;
    private static final int CONTENT_TYPE = 1;
    private PostEntity postEntity;

    public DetailAdapter(PostEntity postEntity) {
        this.postEntity = postEntity;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0 :
                return HEADER_TYPE;
            case 1 :
                return CONTENT_TYPE;
            default:
                throw new IllegalStateException("Invalid layout type");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            return new MetaViewHolder(parent);
        } else if (viewType == CONTENT_TYPE) {
            return new ContentViewHolder(parent);
        } else {
            throw new IllegalStateException("Invalid layout type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MetaViewHolder) {
            MetaViewHolder viewHolder = (MetaViewHolder) holder;
            viewHolder.bind(postEntity);
        } else if (holder instanceof ContentViewHolder) {
            ContentViewHolder viewHolder = (ContentViewHolder) holder;
            viewHolder.bind(postEntity);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(@LayoutRes int resId, ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        }
    }

    public static class MetaViewHolder extends Holder {

        @Bind(R.id.author_date_detail_view) AuthorDateDetailView authorDateDetailView;

        public MetaViewHolder(ViewGroup parent) {
            super(R.layout.meta_view, parent);
            ButterKnife.bind(this, itemView);
        }

        public void bind(PostEntity postEntity) {
            authorDateDetailView.bind(postEntity);
        }
    }

    public static class ContentViewHolder extends Holder {

        @Bind(R.id.title_view) TitleView titleView;
        @Bind(R.id.content) TextView content;

        public ContentViewHolder(ViewGroup parent) {
            super(R.layout.item_content_view, parent);
            ButterKnife.bind(this, itemView);
        }

        public void bind(PostEntity postEntity) {
            titleView.bind(postEntity);
            content.setText(Html.fromHtml(postEntity.content,
                    new ImageGetter(itemView.getContext(), content), null));
        }
    }
}
