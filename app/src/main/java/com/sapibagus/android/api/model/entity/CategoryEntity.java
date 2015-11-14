package com.sapibagus.android.api.model.entity;

public final class CategoryEntity {
    public final long id;
    public final String slug;
    public final String title;
    public final String description;
    public final long parent;
    public final int postCount;

    public CategoryEntity(long id, String slug, String title, String description, long parent,
                          int postCount) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.parent = parent;
        this.postCount = postCount;
    }
}
