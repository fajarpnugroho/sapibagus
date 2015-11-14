package com.sapibagus.android.api.model.entity;

public final class CommentEntity {
    public final long id;
    public final String name;
    public final String url;
    public final String date;
    public final String content;
    public final int parent;

    public CommentEntity(long id, String name, String url, String date, String content, int parent) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.date = date;
        this.content = content;
        this.parent = parent;
    }
}
