package com.sapibagus.android.api.model.entity;

import java.util.List;

public final class PageEntity {
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
    public final List<CommentEntity> comments;
    public final List<AttachmentEntity> attachments;
    public final int commmentCount;
    public final String commentStatus;

    public PageEntity(long id, String type, String slug, String url, String status, String title,
                      String titlePlain, String content, String excerpt,
                      String date, String modified, List<CategoryEntity> categories,
                      List<TagEntity> tags, AuthorEntity author, List<CommentEntity> comments,
                      List<AttachmentEntity> attachments, int commmentCount,
                      String commentStatus) {
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
        this.comments = comments;
        this.attachments = attachments;
        this.commmentCount = commmentCount;
        this.commentStatus = commentStatus;
    }
}
