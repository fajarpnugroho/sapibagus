package com.sapibagus.android.api.model.entity;

public final class CategoryEntity {
    public static final String MENU_TYPE = "menu";
    public static final String PAGE_TYPE = "page";

    public final long id;
    public final String slug;
    public final String title;
    public final String description;
    public final long parent;
    public final int postCount;
    public final String category;
    public final int visible;

    public CategoryEntity(long id, String slug, String title, String description, long parent,
                          int postCount, String category, int visible) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.parent = parent;
        this.postCount = postCount;
        this.category = category;
        this.visible = visible;
    }
}
