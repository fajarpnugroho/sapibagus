package com.sapibagus.android.api.model.entity;

public final class TagEntity {
    public final long id;
    public final String slug;
    public final String title;
    public final String description;
    public final int postCount;

    public TagEntity(long id, String slug, String title, String description, int postCount) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.postCount = postCount;
    }
}
