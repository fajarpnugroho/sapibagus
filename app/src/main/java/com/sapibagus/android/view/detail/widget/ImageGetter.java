package com.sapibagus.android.view.detail.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import timber.log.Timber;

public class ImageGetter implements Html.ImageGetter {

    private final Context mContext;
    private final View mView;

    public ImageGetter(Context context, View view) {
        mContext = context;
        mView = view;
    }

    class URLDrawable extends BitmapDrawable {
        // the drawable that you need to set, you could set the initial drawing
        // with the loading image if you need to
        protected Drawable drawable;

        @Override
        public void draw(Canvas canvas) {
            // override the draw to facilitate refresh function later
            if(drawable != null) {
                drawable.draw(canvas);
            }
        }
    }

    class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        URLDrawable urlDrawable;

        public ImageGetterAsyncTask(URLDrawable d) {
            this.urlDrawable = d;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            Uri uri = Uri.parse(source);
            Bitmap bitmap = null;
            try {
                Picasso pic = new Picasso.Builder(mContext).build();
                bitmap = pic.load(uri).resize(100, 100).get();
            } catch (IOException e) {
                Timber.e(e.getMessage());
            }
            return new BitmapDrawable(mContext.getResources(),bitmap);
        }

        @Override
        protected void onPostExecute(Drawable result) {
            // set the correct bound according to the result from HTTP call
            urlDrawable.setBounds(0, 0, result.getIntrinsicWidth(), result.getIntrinsicHeight());

            // change the reference of the current drawable to the result
            // from the HTTP call
            urlDrawable.drawable = result;

            // redraw the image by invalidating the container
            ImageGetter.this.mView.invalidate();
        }
    }

    @Override
    public Drawable getDrawable(String source) {
        URLDrawable urlDrawable = new URLDrawable();
        ImageGetterAsyncTask task = new ImageGetterAsyncTask(urlDrawable);
        task.execute(source);
        return urlDrawable;
    }
}
