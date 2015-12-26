package com.sapibagus.android.api.model.entity;

import java.util.List;

public final class PostEntity {
    public final long id;
    public final String type;
    public final String slug;
    public final String url;
    public final String status;
    public final String title;
    public final String titlePlain;
    public final String content;
    public final String excerpt;
    public final String date;
    public final String modified;
    public final List<CategoryEntity> categories;
    public final List<TagEntity> tags;
    public final AuthorEntity author;
    public final List<AttachmentEntity> attachments;
    public final int commentCount;
    public final String commentStatus;
    public final ThumbnailImages thumbnailImages;

    public PostEntity(long id, String type, String slug, String url, String status, String title,
                      String titlePlain, String content, String excerpt, String date,
                      String modified, List<CategoryEntity> categories, List<TagEntity> tags,
                      AuthorEntity author, List<AttachmentEntity> attachments, int commentCount,
                      String commentStatus, ThumbnailImages thumbnailImages) {
        this.id = id;
        this.type = type;
        this.slug = slug;
        this.url = url;
        this.status = status;
        this.title = title;
        this.titlePlain = titlePlain;
        this.content = content;
        this.excerpt = excerpt;
        this.date = date;
        this.modified = modified;
        this.categories = categories;
        this.tags = tags;
        this.author = author;
        this.attachments = attachments;
        this.commentCount = commentCount;
        this.commentStatus = commentStatus;
        this.thumbnailImages = thumbnailImages;
    }
}
