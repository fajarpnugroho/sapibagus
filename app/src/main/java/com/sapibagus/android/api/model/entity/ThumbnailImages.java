package com.sapibagus.android.api.model.entity;

public class ThumbnailImages {
    public final Full full;
    public final Thumbnail thumbnail;
    public final Medium medium;

    public ThumbnailImages(Full full, Thumbnail thumbnail, Medium medium) {
        this.full = full;
        this.thumbnail = thumbnail;
        this.medium = medium;
    }

    public class Full {
        public final String url;
        public final int width;
        public final int height;

        public Full(String url, int width, int height) {
            this.url = url;
            this.width = width;
            this.height = height;
        }
    }

    public class Thumbnail {
        public final String url;
        public final int width;
        public final int height;

        public Thumbnail(String url, int width, int height) {
            this.url = url;
            this.width = width;
            this.height = height;
        }
    }

    public class Medium {
        public final String url;
        public final int width;
        public final int height;

        public Medium(String url, int width, int height) {
            this.url = url;
            this.width = width;
            this.height = height;
        }
    }
}
