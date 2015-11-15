package com.sapibagus.android.view.home.adapter;

import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.AttachmentEntity;
import com.sapibagus.android.api.model.entity.PostEntity;
import com.sapibagus.android.view.home.widget.AuthorDateView;
import com.sapibagus.android.view.home.widget.TagsView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostEntity> posts;

    public void setPosts(List<PostEntity> posts) { this.posts = posts; }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PostViewHolder) {
            PostViewHolder viewHolder = (PostViewHolder) holder;
            viewHolder.bind(posts.get(position));
        }
    }

    @Override
    public int getItemCount() { return posts.size(); }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(@LayoutRes int resId, ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        }
    }

    public static class PostViewHolder extends Holder {

        @Bind(R.id.thumbnail) ImageView thumbnail;
        @Bind(R.id.title) TextView title;
        @Bind(R.id.author_date_view) AuthorDateView authorDateView;
        @Bind(R.id.tags_view) TagsView tagsView;

        public PostViewHolder(ViewGroup parent) {
            super(R.layout.item_post_view, parent);
            ButterKnife.bind(this, itemView);
            title.setTypeface(getTypeface("fonts/Arvo-Regular.ttf"));
        }

        public void bind(PostEntity postEntity) {
            title.setText(postEntity.titlePlain);

            // TODO fajar: set visibility image view to gone?
            if (postEntity.attachments.size() > 0) {
                getImageAttachment(postEntity.attachments);
            } else {
                thumbnail.setVisibility(View.GONE);
            }

            authorDateView.bind(postEntity);
            tagsView.bind(postEntity.tags);
        }

        private void getImageAttachment(List<AttachmentEntity> attachments) {
            for (AttachmentEntity attachmentEntity : attachments) {
                // If found attachment with mimeType image set to image view
                if (attachmentEntity.mimeType.contains("image")) {
                    thumbnail.setVisibility(View.VISIBLE);
                    Picasso.with(itemView.getContext())
                            .load(attachmentEntity.url)
                            .fit()
                            .centerCrop()
                            .into(thumbnail);
                    break;
                }
            }
         }

        private Typeface getTypeface(String fontName) {
            return Typeface.createFromAsset(itemView.getContext().getAssets(), fontName);
        }
    }
}
