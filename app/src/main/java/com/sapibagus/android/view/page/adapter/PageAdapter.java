package com.sapibagus.android.view.page.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.sapibagus.android.R;
import com.sapibagus.android.api.model.entity.PageEntity;
import com.sapibagus.android.view.detail.widget.AuthorDateDetailView;
import com.sapibagus.android.view.detail.widget.TitleView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_TYPE = 0;
    private static final int CONTENT_TYPE = 1;

    private final PageEntity pageEntity;

    public PageAdapter(PageEntity pageEntity) { this.pageEntity = pageEntity; }

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
            viewHolder.bind(pageEntity);
        } else if (holder instanceof ContentViewHolder) {
            ContentViewHolder viewHolder = (ContentViewHolder) holder;
            viewHolder.bind(pageEntity);
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

        public void bind(PageEntity pageEntity) {
            authorDateDetailView.bind(pageEntity.author, pageEntity.date);
        }
    }

    public static class ContentViewHolder extends Holder {

        @Bind(R.id.title_view) TitleView titleView;
        @Bind(R.id.content) WebView contentWebView;

        public ContentViewHolder(ViewGroup parent) {
            super(R.layout.item_content_view, parent);
            ButterKnife.bind(this, itemView);

            contentWebView.getSettings().setJavaScriptEnabled(true);

            contentWebView.getSettings().setLayoutAlgorithm(WebSettings
                    .LayoutAlgorithm.SINGLE_COLUMN);

            contentWebView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return (event.getAction() == MotionEvent.ACTION_MOVE);
                }
            });
        }

        public void bind(PageEntity pageEntity) {
            titleView.bind(pageEntity.attachments, pageEntity.titlePlain);

            StringBuilder sb = new StringBuilder();
            sb.append("<HTML><HEAD>"
                    + "<LINK href=\"http://www.sapibagus.com/wp-content/plugins/yet-another-related-posts-plugin/style/related.css?ver=4.3.1\" type=\"text/css\" rel=\"stylesheet\"/>"
                    + "<LINK href=\"http://www.sapibagus.com/wp-content/plugins/yet-another-related-posts-plugin/includes/styles_thumbnails.css.php?width=120&height=120&ver=4.2.5\" type=\"text/css\" rel=\"stylesheet\"/>"
                    + "<STYLE>iframe { height: 1000px; }</STYLE>"
                    + "</HEAD><body>");
            sb.append(pageEntity.content);
            sb.append("</BODY></HTML>");


            contentWebView.loadData(sb.toString(), "text/html", Xml.Encoding.US_ASCII.toString());
        }
    }
}
