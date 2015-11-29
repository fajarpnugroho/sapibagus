package com.sapibagus.android.view.detail.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.sapibagus.android.R;
import com.sapibagus.android.view.home.widget.LoadingView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebLoadingView extends FrameLayout {

    @Bind(R.id.content) WebView contentWebView;
    @Bind(R.id.loading_view) LoadingView loadingView;

    public WebLoadingView(Context context) {
        this(context, null);
    }

    public WebLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.webloading_view, this);

        ButterKnife.bind(this);

        if (!isInEditMode()) {

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
    }

    public void bind(String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("<HTML><HEAD>"
                + "<LINK href=\"http://www.sapibagus.com/wp-content/plugins/yet-another-related-posts-plugin/style/related.css?ver=4.3.1\" type=\"text/css\" rel=\"stylesheet\"/>"
                + "<LINK href=\"http://www.sapibagus.com/wp-content/plugins/yet-another-related-posts-plugin/includes/styles_thumbnails.css.php?width=120&height=120&ver=4.2.5\" type=\"text/css\" rel=\"stylesheet\"/>"
                + "</HEAD><body>");
        sb.append(content);
        sb.append("</BODY></HTML>");


        contentWebView.loadData(sb.toString(), "text/html", Xml.Encoding.US_ASCII.toString());
        contentWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                loadingView.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingView.hide();
            }
        });
    }
}
