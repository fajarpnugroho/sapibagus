package com.sapibagus.android.api.model.entity;

public final class AttachmentEntity {
    public final long id;
    public final String url;
    public final String slug;
    public final String title;
    public final String description;
    public final String caption;
    public final long parent;
    public final String mimyType;

    public AttachmentEntity(long id, String url, String slug, String title, String description, String caption, long parent, String mimyType) {
        this.id = id;
        this.url = url;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.caption = caption;
        this.parent = parent;
        this.mimyType = mimyType;
    }
}
