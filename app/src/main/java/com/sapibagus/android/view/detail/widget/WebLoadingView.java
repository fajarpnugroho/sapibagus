package com.sapibagus.android.view.detail.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.LayoutInflater;
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

    private Listener listener;

    public WebLoadingView(Context context) {
        this(context, null);
    }

    public WebLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public WebLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.webloading_view, this);

        ButterKnife.bind(this);

        if (!isInEditMode()) {
            contentWebView.getSettings().setJavaScriptEnabled(true);
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void onLoading(boolean loading) {
        if (loading) {
            loadingView.show();
        } else {
            loadingView.hide();
        }
    }

    public void bind(final String title, String content) {
        contentWebView.loadData("<HTML><HEAD>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />"
                + "<LINK href=\"http://www.sapibagus.com/wp-content/themes/Observer/style.css\" "
                        + "type=\"text/css\" rel=\"stylesheet\"/>"
                + "<LINK href=\"http://www.sapibagus.com/wp-content/plugins/"
                        + "yet-another-related-posts-plugin/style/related.css?ver=4.3.1\" "
                        + "type=\"text/css\" rel=\"stylesheet\"/>"
                + "<LINK href=\"http://www.sapibagus.com/wp-content/plugins/"
                        + "yet-another-related-posts-plugin/includes/"
                        + "styles_thumbnails.css.php?width=120&height=120&ver=4.2.5\" "
                        + "type=\"text/css\" rel=\"stylesheet\"/>"
                + "<STYLE>"
                        + "body { padding: 20px; }"
                        + "iframe { height: 1000px; max-width: 100% }"
                        + "p, h1 { line-height: 1.5; font-family: Lucida,helvetica,sans-serif; }"
                        + "h1 { font-size: 22px; }"
                        + "p, h2 { padding-bottom: 20px; }"
                        + "</STYLE>"
                + "</HEAD><body>"
                        + String.format("<h1>%s</h1>", title)
                        + "<p>&nbsp;</p>"
                        + content
                        + "</BODY></HTML>",
                "text/html",
                Xml.Encoding.UTF_8.toString());

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

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);

                if (uri.getHost().equals("www.sapibagus.com")) {
                    listener.openRelatedArticle(title, url);
                }
                else {
                    listener.openLinkInsideContent(title, url);
                }
                return true;
            }
        });
    }

    public interface Listener {
        void openLinkInsideContent(String title, String url);

        void openRelatedArticle(String title, String url);
    }
}
