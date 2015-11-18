package com.sapibagus.android.view.home.adapter;

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
import com.sapibagus.android.utils.AssetUtils;
import com.sapibagus.android.utils.ImageUtils;
import com.sapibagus.android.view.home.widget.AuthorDateView;
import com.sapibagus.android.view.home.widget.TagsView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostEntity> posts;

    private Listener listener;

    public PostsAdapter(Listener listener) {
        this.listener = listener;
    }

    public void setPosts(List<PostEntity> posts) { this.posts = posts; }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(parent, listener);
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

        private Listener listener;

        public PostViewHolder(ViewGroup parent, Listener listener) {
            super(R.layout.item_post_view, parent);
            ButterKnife.bind(this, itemView);
            title.setTypeface(AssetUtils.getTypeface(itemView.getContext(),
                    "fonts/Arvo-Regular.ttf"));
            this.listener = listener;
        }

        public void bind(final PostEntity postEntity) {
            title.setText(postEntity.titlePlain);

            AttachmentEntity attachmentEntity = ImageUtils
                    .getImageAttachment(postEntity.attachments);

            if (attachmentEntity != null) {
                thumbnail.setVisibility(View.VISIBLE);
                Picasso.with(itemView.getContext())
                        .load(attachmentEntity.url)
                        .fit()
                        .centerCrop()
                        .into(thumbnail);
            } else {
                thumbnail.setVisibility(View.GONE);
            }

            authorDateView.bind(postEntity);
            tagsView.bind(postEntity.tags);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClickListener(postEntity);
                }
            });
        }
    }

    public interface Listener {
        void itemClickListener(PostEntity postEntity);
    }
}
